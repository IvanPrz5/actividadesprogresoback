package com.example.actividadesProgreso.Service.Actividades.Utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Actividades.JerarquiaActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.StatusActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.TipoActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.DTO.ActividadDTO2;
import com.example.actividadesProgreso.Entity.Actividades.Interface.ActividadInterface;
import com.example.actividadesProgreso.Entity.Clientes.ClientesEntity;
import com.example.actividadesProgreso.Entity.Empresas.EmpresasEntity;
import com.example.actividadesProgreso.Entity.Usuarios.UsuariosEntity;
import com.example.actividadesProgreso.Repository.Actividades.ActividadRepository;
import com.example.actividadesProgreso.Repository.Actividades.BienActividadRepository;
import com.example.actividadesProgreso.Repository.Actividades.JerarquiaActividadRepository;
import com.example.actividadesProgreso.Repository.Actividades.StatusActividadRepository;
import com.example.actividadesProgreso.Repository.Actividades.TipoActividadRepository;
import com.example.actividadesProgreso.Repository.Clientes.ClientesRepository;
import com.example.actividadesProgreso.Repository.Empresas.EmpresasRepository;
import com.example.actividadesProgreso.Repository.Usuarios.UsuariosRepository;

@Service
public class ActividadUtils {
    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    EmpresasRepository empresasRepository;

    @Autowired
    JerarquiaActividadRepository jerarquiaActividadRepository;

    @Autowired
    StatusActividadRepository statusActividadRepository;

    @Autowired
    TipoActividadRepository tipoActividadRepository;

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    BienActividadRepository bienActividadRepository;

    @Autowired
    ClientesRepository clientesRepository;

    public List<ActividadDTO2> empresas(List<ActividadInterface> actividadInterface,
            List<ActividadDTO2> listaActividad, Long idUsuario, Long idEmpresa, int i) {
        List<EmpresasEntity> empresasEntity = empresasRepository
                .findByIdAndStatus(actividadInterface.get(i).getId_Empresa(), true);
        for (int k = 0; k < empresasEntity.size(); k++) {
            listaActividad.get(i).getEmpresa().put("id", empresasEntity.get(k).getId());
            listaActividad.get(i).getEmpresa().put("descripcion", empresasEntity.get(k).getDescripcion());
        }
        return listaActividad;
    }

    public List<ActividadDTO2> jerarquiaActividad(List<ActividadInterface> actividadInterface,
            List<ActividadDTO2> listaActividad, Long idUsuario, Long idEmpresa, int i) {
        List<JerarquiaActividadEntity> jerarquiaEntity = jerarquiaActividadRepository
                .findByIdAndStatus(actividadInterface.get(i).getId_Jerarquia_Actividad(), true);
        for (int k = 0; k < jerarquiaEntity.size(); k++) {
            listaActividad.get(i).getJerarquiaActividad().put("id", jerarquiaEntity.get(k).getId());
            listaActividad.get(i).getJerarquiaActividad().put("descripcion", jerarquiaEntity.get(k).getDescripcion());
        }
        return listaActividad;
    }

    public List<ActividadDTO2> statusActividad(List<ActividadInterface> actividadInterface,
            List<ActividadDTO2> listaActividad, Long idUsuario, Long idEmpresa, int i) {
        List<StatusActividadEntity> statusActividad = statusActividadRepository
                .findByIdAndStatus(actividadInterface.get(i).getId_Status_Actividad(), true);
        for (int k = 0; k < statusActividad.size(); k++) {
            listaActividad.get(i).getStatusActividad().put("id", statusActividad.get(k).getId());
            listaActividad.get(i).getStatusActividad().put("descripcion", statusActividad.get(k).getDescripcion());
        }
        return listaActividad;
    }

    public List<ActividadDTO2> tipoActividad(List<ActividadInterface> actividadInterface,
            List<ActividadDTO2> listaActividad, Long idUsuario, Long idEmpresa, int i) {
        List<TipoActividadEntity> tipoActividad = tipoActividadRepository
                .findByIdAndStatus(actividadInterface.get(i).getId_Tipo_Actividad(), true);
        for (int k = 0; k < tipoActividad.size(); k++) {
            listaActividad.get(i).getTipoActividad().put("id", tipoActividad.get(k).getId());
            listaActividad.get(i).getTipoActividad().put("descripcion", tipoActividad.get(k).getDescripcion());
        }
        return listaActividad;
    }

    public List<ActividadDTO2> usuariosAsigno(List<ActividadInterface> actividadInterface,
            List<ActividadDTO2> listaActividad, Long idUsuario, Long idEmpresa, int i) {
        List<UsuariosEntity> usuarioInicio = usuariosRepository
                .findByIdAndStatus(actividadInterface.get(i).getId_Usuario_Asigno(), true);
        for (int k = 0; k < usuarioInicio.size(); k++) {
            listaActividad.get(i).getUsuarioAsigno().put("id", usuarioInicio.get(k).getId());
            listaActividad.get(i).getUsuarioAsigno().put("descripcion", usuarioInicio.get(k).getNombre() + " "  + usuarioInicio.get(k).getApPaterno());
        }
        return listaActividad;
    }

    public List<ActividadDTO2> usuariosTermino(List<ActividadInterface> actividadInterface,
            List<ActividadDTO2> listaActividad, Long idUsuario, Long idEmpresa, int i) {
        List<UsuariosEntity> usuarioTermino = usuariosRepository
                .findByIdAndStatus(actividadInterface.get(i).getId_Usuario_Termino(), true);
        for (int k = 0; k < usuarioTermino.size(); k++) {
            listaActividad.get(i).getUsuarioTermino().put("id", usuarioTermino.get(k).getId());
            listaActividad.get(i).getUsuarioTermino().put("descripcion", usuarioTermino.get(k).getNombre() + " "  + usuarioTermino.get(k).getApPaterno());
        }
        return listaActividad;
    }

    public List<ActividadDTO2> clientes(List<ActividadInterface> actividadInterface,
            List<ActividadDTO2> listaActividad, Long idUsuario, Long idEmpresa, int i) {
        List<ClientesEntity> clientes = clientesRepository
                .findByIdAndStatus(actividadInterface.get(i).getId_Clientes(), true);
        for (int k = 0; k < clientes.size(); k++) {
            listaActividad.get(i).getCliente().put("id", clientes.get(k).getId());
            listaActividad.get(i).getCliente().put("descripcion", clientes.get(k).getDescripcion());
        }
        return listaActividad;
    }
}
