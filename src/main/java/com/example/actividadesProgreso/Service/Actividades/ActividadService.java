package com.example.actividadesProgreso.Service.Actividades;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Actividades.ActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.JerarquiaActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.StatusActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.TipoActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.DTO.ActividadDTO;
import com.example.actividadesProgreso.Entity.Actividades.DTO.ActividadDTO2;
import com.example.actividadesProgreso.Entity.Actividades.Interface.ActividadInterface;
import com.example.actividadesProgreso.Entity.Clientes.ClientesEntity;
import com.example.actividadesProgreso.Entity.Empresas.EmpresasEntity;
import com.example.actividadesProgreso.Entity.Usuarios.UsuariosEntity;
import com.example.actividadesProgreso.Repository.Actividades.ActividadRepository;
import com.example.actividadesProgreso.Repository.Actividades.JerarquiaActividadRepository;
import com.example.actividadesProgreso.Repository.Actividades.StatusActividadRepository;
import com.example.actividadesProgreso.Repository.Actividades.TipoActividadRepository;
import com.example.actividadesProgreso.Repository.Empresas.EmpresasRepository;
import com.example.actividadesProgreso.Repository.Usuarios.UsuariosRepository;
import com.example.actividadesProgreso.Service.Actividades.Utils.ActividadUtils;

@Service
public class ActividadService {

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    EmpresasRepository empresasRepository;

    @Autowired
    JerarquiaActividadRepository jerarquiaActividadRepository;

    @Autowired
    StatusActividadRepository statusActividadRepository;

    @Autowired
    TipoActividadRepository tipoActividadRepository;

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    ActividadUtils actividadUtils;

    public Optional<ActividadEntity> getById(Long id) {
        try {
            Optional<ActividadEntity> actividad = actividadRepository.findById(id);
            return actividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ActividadDTO> getByIdAndStatus(Long id, Boolean status) {
        try {
            List<ActividadEntity> actividadEntity = actividadRepository.findByIdAndStatus(id, status);
            EmpresasEntity empresa = actividadEntity.get(0).getIdEmpresa();
            TipoActividadEntity tipoActividad = actividadEntity.get(0).getIdTipoActividad();
            StatusActividadEntity statusActividad = actividadEntity.get(0).getIdStatusActividad();
            UsuariosEntity usuarioAsigno = actividadEntity.get(0).getIdUsuarioAsigno();
            UsuariosEntity usuarioTermino = actividadEntity.get(0).getIdUsuarioTermino();
            JerarquiaActividadEntity jerarquiaActividad = actividadEntity.get(0).getIdJerarquiaActividad();
            ClientesEntity clientes = actividadEntity.get(0).getIdClientes();

            List<ActividadDTO> actividadDTO = actividadEntity.stream()
                    .map(this::entityToDTO)
                    .collect(Collectors.toList());

            actividadDTO.get(0).getEmpresa().put("id", empresa.getId());
            actividadDTO.get(0).getEmpresa().put("descripcion", empresa.getDescripcion());
            actividadDTO.get(0).getTipoActividad().put("id", tipoActividad.getId());
            actividadDTO.get(0).getTipoActividad().put("descripcion", tipoActividad.getDescripcion());
            actividadDTO.get(0).getStatusActividad().put("id", statusActividad.getId());
            actividadDTO.get(0).getStatusActividad().put("descripcion", statusActividad.getDescripcion());
            actividadDTO.get(0).getUsuarioAsigno().put("id", usuarioAsigno.getId());
            actividadDTO.get(0).getUsuarioAsigno().put("descripcion", usuarioAsigno.getNombre());
            actividadDTO.get(0).getUsuarioTermino().put("id", usuarioTermino.getId());
            actividadDTO.get(0).getUsuarioTermino().put("descripcion", usuarioTermino.getNombre());
            actividadDTO.get(0).getJerarquiaActividad().put("id", jerarquiaActividad.getId());
            actividadDTO.get(0).getJerarquiaActividad().put("descripcion", jerarquiaActividad.getDescripcion());
            actividadDTO.get(0).getJerarquiaActividad().put("nivel", jerarquiaActividad.getNivel());
            actividadDTO.get(0).getClientes().put("id", clientes.getId());
            actividadDTO.get(0).getClientes().put("descripcion", clientes.getDescripcion());

            return actividadDTO;
        } catch (Exception e) {
            return null;
        }
    }

    public ActividadDTO entityToDTO(ActividadEntity actividad) {
        return new ActividadDTO(
                actividad);
    }

    public List<ActividadEntity> getByStatus2(Boolean status) {
        try {
            List<ActividadEntity> actividad = actividadRepository.findByStatus(status);
            return actividad;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<String> addRegister(ActividadEntity var) {
        try {
            ActividadEntity actividadEntity = actividadRepository.save(var);
            return new ResponseEntity<>(actividadEntity.getId().toString(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ActividadEntity> editRegister(Long id, ActividadEntity object) {
        Optional<ActividadEntity> actividadId = actividadRepository.findById(id);

        if (actividadId.isPresent()) {
            actividadRepository.save(object);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ActividadEntity> editStatus(Long id, ActividadEntity object) {
        Optional<ActividadEntity> actividadId = actividadRepository.findById(id);
        if (actividadId.isPresent()) {
            ActividadEntity actividadEntity = actividadId.get();
            actividadEntity.setIdUsuarioTermino(object.getIdUsuarioTermino());
            actividadEntity.setFechaFinalizoUser(object.getFechaFinalizoUser());
            actividadEntity.setStatus(object.getStatus());
            actividadRepository.save(actividadEntity);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Sub Consultas
    public List<String> cuentaActividades(Long idUsuario, Long idEmpresa) {
        try {
            return actividadRepository.cuentaActividades(idUsuario, idEmpresa);
        } catch (Exception e) {
            return null;
        }
    }

    public List<ActividadDTO2> consultaTerminanHoyAndVencidas(Long idUsuario, Long idEmpresa) {
        try {
            List<ActividadInterface> actividadInterface = actividadRepository.actividadTerminanHoyAndVencidas(idUsuario,
                    idEmpresa);
            List<ActividadDTO2> listaActividad = new ArrayList<>();
            for (int i = 0; i < actividadInterface.size(); i++) {
                ActividadDTO2 listaActividad2 = new ActividadDTO2();
                listaActividad2.convertDto(actividadInterface.get(i));
                listaActividad.add(listaActividad2);
                actividadUtils.empresas(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.jerarquiaActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.statusActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.tipoActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosAsigno(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosTermino(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.clientes(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
            }
            return listaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ActividadDTO2> consultaAsigneDiaria(Long idUsuario, Long idEmpresa, Boolean status) {
        try {
            List<ActividadInterface> actividadInterface = actividadRepository.consultaAsigneDiaria(idUsuario, idEmpresa,
                    status);
            List<ActividadDTO2> listaActividad = new ArrayList<>();
            for (int i = 0; i < actividadInterface.size(); i++) {
                ActividadDTO2 listaActividad2 = new ActividadDTO2();
                listaActividad2.convertDto(actividadInterface.get(i));
                listaActividad.add(listaActividad2);
                actividadUtils.empresas(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.jerarquiaActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.statusActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.tipoActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosAsigno(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosTermino(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.clientes(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
            }
            return listaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ActividadDTO2> consultaAsigneSemanal(Long idUsuario, Long idEmpresa, Boolean status) {
        try {
            List<ActividadInterface> actividadInterface = actividadRepository.consultaAsigneSemanal(idUsuario,
                    idEmpresa, status);
            List<ActividadDTO2> listaActividad = new ArrayList<>();
            for (int i = 0; i < actividadInterface.size(); i++) {
                ActividadDTO2 listaActividad2 = new ActividadDTO2();
                listaActividad2.convertDto(actividadInterface.get(i));
                listaActividad.add(listaActividad2);
                actividadUtils.empresas(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.jerarquiaActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.statusActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.tipoActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosAsigno(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosTermino(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.clientes(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
            }
            return listaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ActividadDTO2> consutalAsigneMensual(Long idUsuario, Long idEmpresa, Boolean status) {
        try {
            List<ActividadInterface> actividadInterface = actividadRepository.consultaAsigneMensual(idUsuario,
                    idEmpresa, status);
            List<ActividadDTO2> listaActividad = new ArrayList<>();
            for (int i = 0; i < actividadInterface.size(); i++) {
                ActividadDTO2 listaActividad2 = new ActividadDTO2();
                listaActividad2.convertDto(actividadInterface.get(i));
                listaActividad.add(listaActividad2);
                actividadUtils.empresas(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.jerarquiaActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.statusActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.tipoActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosAsigno(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosTermino(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.clientes(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
            }
            return listaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ActividadDTO2> consultaAsignaronDiaria(Long idUsuario, Long idEmpresa, Boolean status) {
        try {
            List<ActividadInterface> actividadInterface = actividadRepository.consultaAsignaronDiaria(idUsuario,
                    idEmpresa, status);
            List<ActividadDTO2> listaActividad = new ArrayList<>();
            for (int i = 0; i < actividadInterface.size(); i++) {
                ActividadDTO2 listaActividad2 = new ActividadDTO2();
                listaActividad2.convertDto(actividadInterface.get(i));
                listaActividad.add(listaActividad2);
                actividadUtils.empresas(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.jerarquiaActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.statusActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.tipoActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosAsigno(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosTermino(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.clientes(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
            }
            return listaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ActividadDTO2> consultaAsignaronSemanal(Long idUsuario, Long idEmpresa, Boolean status) {
        try {
            List<ActividadInterface> actividadInterface = actividadRepository.consultaAsignaronSemanal(idUsuario,
                    idEmpresa, status);
            List<ActividadDTO2> listaActividad = new ArrayList<>();
            for (int i = 0; i < actividadInterface.size(); i++) {
                ActividadDTO2 listaActividad2 = new ActividadDTO2();
                listaActividad2.convertDto(actividadInterface.get(i));
                listaActividad.add(listaActividad2);
                actividadUtils.empresas(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.jerarquiaActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.statusActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.tipoActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosAsigno(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosTermino(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.clientes(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
            }
            return listaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ActividadDTO2> consultaAsignaronMensual(Long idUsuario, Long idEmpresa, Boolean status) {
        try {
            List<ActividadInterface> actividadInterface = actividadRepository.consultaAsignaronMensual(idUsuario,
                    idEmpresa, status);
            List<ActividadDTO2> listaActividad = new ArrayList<>();
            for (int i = 0; i < actividadInterface.size(); i++) {
                ActividadDTO2 listaActividad2 = new ActividadDTO2();
                listaActividad2.convertDto(actividadInterface.get(i));
                listaActividad.add(listaActividad2);
                actividadUtils.empresas(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.jerarquiaActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.statusActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.tipoActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosAsigno(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosTermino(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.clientes(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
            }
            return listaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ActividadDTO2> consultaDiariaTodas(Long idUsuario, Long idEmpresa, Boolean status) {
        try {
            List<ActividadInterface> actividadInterface = actividadRepository.consultaDiariaTodas(idUsuario, idEmpresa,
                    status);
            List<ActividadDTO2> listaActividad = new ArrayList<>();
            for (int i = 0; i < actividadInterface.size(); i++) {
                ActividadDTO2 listaActividad2 = new ActividadDTO2();
                listaActividad2.convertDto(actividadInterface.get(i));
                listaActividad.add(listaActividad2);
                actividadUtils.empresas(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.jerarquiaActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.statusActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.tipoActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosAsigno(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosTermino(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.clientes(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
            }
            return listaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ActividadDTO2> consultaSemanalTodas(Long idUsuario, Long idEmpresa, Boolean status) {
        try {
            List<ActividadInterface> actividadInterface = actividadRepository.consultaSemanalTodas(idUsuario, idEmpresa,
                    status);
            List<ActividadDTO2> listaActividad = new ArrayList<>();
            for (int i = 0; i < actividadInterface.size(); i++) {
                ActividadDTO2 listaActividad2 = new ActividadDTO2();
                listaActividad2.convertDto(actividadInterface.get(i));
                listaActividad.add(listaActividad2);
                actividadUtils.empresas(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.jerarquiaActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.statusActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.tipoActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosAsigno(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosTermino(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.clientes(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
            }
            return listaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ActividadDTO2> consutalMensualTodas(Long idUsuario, Long idEmpresa, Boolean status) {
        try {
            List<ActividadInterface> actividadInterface = actividadRepository.consultaMensualTodas(idUsuario, idEmpresa,
                    status);
            List<ActividadDTO2> listaActividad = new ArrayList<>();
            for (int i = 0; i < actividadInterface.size(); i++) {
                ActividadDTO2 listaActividad2 = new ActividadDTO2();
                listaActividad2.convertDto(actividadInterface.get(i));
                listaActividad.add(listaActividad2);
                actividadUtils.empresas(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.jerarquiaActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.statusActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.tipoActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosAsigno(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosTermino(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.clientes(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
            }
            return listaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ActividadDTO2> getActividadesPorDia(Long idUsuario, String fechaInicio, Long idEmpresa) {
        try {
            List<ActividadInterface> actividadInterface = actividadRepository.getActividadesPorDia(idUsuario,
                    fechaInicio, idEmpresa);
            List<ActividadDTO2> listaActividad = new ArrayList<>();
            for (int i = 0; i < actividadInterface.size(); i++) {
                ActividadDTO2 listaActividad2 = new ActividadDTO2();
                listaActividad2.convertDto(actividadInterface.get(i));
                listaActividad.add(listaActividad2);
                actividadUtils.empresas(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.jerarquiaActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.statusActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.tipoActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosAsigno(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosTermino(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.clientes(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
            }
            return listaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ActividadDTO2> getActividadesEnMes(Long idUsuario, Long idEmpresa, String fechaInicio,
            String fechaFin) {
        try {
            List<ActividadInterface> actividadInterface = actividadRepository.getActividadesEnMes(idUsuario, idEmpresa,
                    fechaInicio, fechaFin);
            List<ActividadDTO2> listaActividad = new ArrayList<>();
            for (int i = 0; i < actividadInterface.size(); i++) {
                ActividadDTO2 listaActividad2 = new ActividadDTO2();
                listaActividad2.convertDto(actividadInterface.get(i));
                listaActividad.add(listaActividad2);
                actividadUtils.empresas(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.jerarquiaActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.statusActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.tipoActividad(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosAsigno(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.usuariosTermino(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
                actividadUtils.clientes(actividadInterface, listaActividad, idUsuario, idEmpresa, i);
            }
            return listaActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<String> addActividadesMensuales(ActividadEntity var) {
        try {
            ActividadEntity actividadEntity = actividadRepository.save(var);
            return new ResponseEntity<>(actividadEntity.getId().toString(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addRegister2(List<ActividadEntity> var) {
        try {
            // ActividadEntity actividadEntity = new ActividadEntity();
            actividadRepository.insertIntoActividades("RECEPCIÓN DE DOCUMENTACIÓN COMPROBATORIA MENSUAL",
                    var.get(0).getFechaFin(), var.get(0).getFechaInicio(),
                    true, 1L, 1L, 1L, 2L, 7L, true);

            actividadRepository.insertIntoActividades("ELABORACIÓN DE CORTES DE CAJA (INGRESOS Y EGRESOS MENSUAL)",
                    var.get(1).getFechaFin(), var.get(1).getFechaInicio(),
                    true, 1L, 1L, 1L, 2L, 7L, true);

            actividadRepository.insertIntoActividades("TIMBRADO INGRESOS MUNICIPALES", 
                    var.get(2).getFechaFin(), var.get(2).getFechaInicio(),
                    true, 1L, 1L, 1L, 2L, 7L, true);

            actividadRepository.insertIntoActividades("TIMBRADO Y ENTREGA DE PARTICIPACIONES MUNICIPALES",
                    var.get(3).getFechaFin(), var.get(3).getFechaInicio(),
                    true, 1L, 1L, 1L, 2L, 7L, true);

            actividadRepository.insertIntoActividades("CAPTURA EN EL SIMCA", 
                    var.get(4).getFechaFin(), var.get(4).getFechaInicio(),
                    true, 1L, 1L, 1L, 2L, 7L, true);

            actividadRepository.insertIntoActividades("ENTREGA DE REPORTE PREDIAL Y AGUA MENSUAL", 
                    var.get(5).getFechaFin(), var.get(5).getFechaInicio(),
                    true, 1L, 1L, 1L, 2L, 7L, true);

            actividadRepository.insertIntoActividades("PRESENTACIÓN DE DECLARACION DE ISR ASIMILADOS",
                    var.get(6).getFechaFin(), var.get(6).getFechaInicio(),
                    true, 1L, 1L, 1L, 2L, 7L, true);

            actividadRepository.insertIntoActividades("PRESENTACIÓN DE DECLARACION DE ISR 1.25% RESICO Y SERV. PROF.",
                    var.get(7).getFechaFin(), var.get(7).getFechaInicio(),
                    true, 1L, 1L, 1L, 2L, 7L, true);

            actividadRepository.insertIntoActividades("DECLARACIÓN INFORMATIVA (DIOT) MENSUAL", 
                    var.get(8).getFechaFin(), var.get(8).getFechaInicio(),
                    true, 1L, 1L, 1L, 2L, 7L, true);

            return new ResponseEntity<>("OK", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * public List<ActividadDTO2> getActividadesByIsMensualTrue(Long idUsuario, Long
     * idEmpresa, String fechaInicio, String fechaFin){
     * try {
     * List<ActividadInterface> actividadInterface =
     * actividadRepository.getActividadesByIsMensualTrue(idUsuario, idEmpresa,
     * fechaInicio, fechaFin);
     * List<ActividadDTO2> listaActividad = new ArrayList<>();
     * for(int i=0; i<actividadInterface.size(); i++){
     * ActividadDTO2 listaActividad2 = new ActividadDTO2();
     * listaActividad2.convertDto(actividadInterface.get(i));
     * listaActividad.add(listaActividad2);
     * actividadUtils.empresas(actividadInterface, listaActividad, idUsuario,
     * idEmpresa, i);
     * actividadUtils.jerarquiaActividad(actividadInterface, listaActividad,
     * idUsuario, idEmpresa, i);
     * actividadUtils.statusActividad(actividadInterface, listaActividad, idUsuario,
     * idEmpresa, i);
     * actividadUtils.tipoActividad(actividadInterface, listaActividad, idUsuario,
     * idEmpresa, i);
     * actividadUtils.usuariosAsigno(actividadInterface, listaActividad, idUsuario,
     * idEmpresa, i);
     * actividadUtils.usuariosTermino(actividadInterface, listaActividad, idUsuario,
     * idEmpresa, i);
     * actividadUtils.clientes(actividadInterface, listaActividad, idUsuario,
     * idEmpresa, i);
     * }
     * return listaActividad;
     * } catch (Exception e) {
     * return null;
     * }
     * }
     */

}