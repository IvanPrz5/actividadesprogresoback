package com.example.actividadesProgreso.Service.Actividades;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.example.actividadesProgreso.Entity.Actividades.ActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.ArchivoActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.DTO.ArchivoDTO;
import com.example.actividadesProgreso.Entity.Actividades.DTO.GlobalDTO;
import com.example.actividadesProgreso.Repository.Actividades.ActividadRepository;
import com.example.actividadesProgreso.Repository.Actividades.ArchivoActividadRepository;

@Service
public class ArchivoActividadService {

    @Autowired
    ArchivoActividadRepository archivoActividadRepository;

    @Autowired
    ActividadRepository actividadRepository;

    public List<GlobalDTO> getById(Long id) {
        try {
            Optional<ArchivoActividadEntity> archivoActividad = archivoActividadRepository.findById(id);
            return archivoActividad.stream()
                    .map(this::entityToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public List<GlobalDTO> getByStatus(Boolean status) {
        try {
            List<ArchivoActividadEntity> archivoActividad = archivoActividadRepository.findByStatus(status);
            return archivoActividad.stream()
                    .map(this::entityToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public List<GlobalDTO> getByIdActividadAndStatus(Long id) {
        try {
            Optional<ActividadEntity> actividad = actividadRepository.findById(id);
            ActividadEntity actividadId = actividad.get();
            Sort sort = Sort.by("id").ascending();
            List<ArchivoActividadEntity> archivoActividad = archivoActividadRepository
                    .findByIdActividadAndStatus(actividadId, true, sort);
            return archivoActividad.stream()
                    .map(this::entityToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public GlobalDTO entityToDTO(ArchivoActividadEntity archivoActividadEntity) {
        return new GlobalDTO(
                archivoActividadEntity);
    }

    /* ----------------- ARCHIVOS -------------------- */
    public String guardarArchivo(List<MultipartFile> multipartFile, ArchivoActividadEntity doc) {
        if (!multipartFile.isEmpty()) {
            try {
                String rutaCarpeta = System.getProperty("user.dir")
                        + "\\src\\main\\java\\com\\example\\actividadesProgreso\\Archivos\\ArchivoActividad\\" +
                        doc.getIdActividad().getId();
                        
                FileUtils.forceMkdir(new File(rutaCarpeta));
                for (int i = 0; i < multipartFile.size(); i++) {
                    ArchivoActividadEntity archivoActividad = new ArchivoActividadEntity();
                    byte[] bytes = multipartFile.get(i).getBytes();
                    Path path = Paths.get(rutaCarpeta + "/" + multipartFile.get(i).getOriginalFilename());
                    Files.write(path, bytes);
                    archivoActividad.setNombre(multipartFile.get(i).getOriginalFilename());
                    archivoActividad.setTipoArchivo(multipartFile.get(i).getContentType());
                    archivoActividad.setRuta(path.toString());
                    archivoActividad.setIcon(doc.getIcon());
                    archivoActividad.setUrl(doc.getUrl());
                    archivoActividad.setIdUsuario(doc.getIdUsuario());
                    archivoActividad.setIdActividad(doc.getIdActividad());
                    archivoActividadRepository.save(archivoActividad);
                }
                return "Archivo Guardado";
            } catch (IOException e) {
                return "Error " + e;
            }
        }
        return null;
    }

    public String contestarAlArchivo(List<MultipartFile> multipartFile, ArchivoActividadEntity doc) {
        if (!multipartFile.isEmpty()) {
            try {
                String rutaCarpeta = System.getProperty("user.dir")
                        + "\\src\\main\\java\\com\\example\\actividadesProgreso\\Archivos\\ArchivoActividad\\" + doc.getIdActividad().getId();
                FileUtils.forceMkdir(new File(rutaCarpeta));
                ArchivoActividadEntity archivoActividad = new ArchivoActividadEntity();
                for (int i = 0; i < multipartFile.size(); i++) {
                    byte[] bytes = multipartFile.get(i).getBytes();
                    Path path = Paths.get(rutaCarpeta + "/" + multipartFile.get(i).getOriginalFilename());
                    Files.write(path, bytes);
                    archivoActividad.setRuta(path.toString());
                    archivoActividad.setUrl(doc.getUrl());
                    archivoActividad.setIdActividad(doc.getIdActividad());
                    archivoActividad.setIdArchivoActividad(doc.getIdArchivoActividad());
                    archivoActividad.setIdUsuario(doc.getIdUsuario());
                    archivoActividad.setNombre(multipartFile.get(i).getOriginalFilename());
                    archivoActividad.setTipoArchivo(multipartFile.get(i).getContentType());
                    archivoActividad.setIcon(doc.getIcon());
                    archivoActividadRepository.save(archivoActividad);
                }
                return "Archivo Guardado";
            } catch (IOException e) {
                return "Error " + e;
            }
        }
        return null;
    }

    public ResponseEntity<Resource> descargarArchivo(Long id){
        try {
            ArchivoActividadEntity archivoActividadEntity = null;
            archivoActividadEntity = archivoActividadRepository.findById(id).orElseThrow();
            String rutaFile = archivoActividadEntity.getRuta();
            byte[] array = Files.readAllBytes(Paths.get(rutaFile));
            String headerValue = "attachment; filename=\"" + archivoActividadEntity.getNombre() + "\""; 
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(archivoActividadEntity.getTipoArchivo()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .body(new ByteArrayResource(array));
        } catch (Exception e) {
            return null;
        }
    }
    /* ----------------- ARCHIVOS TERMINA AQUI -------------------- */
    public ResponseEntity<ArchivoActividadEntity> addRegister(ArchivoActividadEntity var) {
        try {
            archivoActividadRepository.save(var);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ArchivoActividadEntity> editRegister(Long id, ArchivoActividadEntity object) {
        Optional<ArchivoActividadEntity> archivoActividadId = archivoActividadRepository.findById(id);

        if (archivoActividadId.isPresent()) {
            archivoActividadRepository.save(object);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ArchivoActividadEntity> editStatus(Long id, ArchivoActividadEntity object) {
        Optional<ArchivoActividadEntity> actividadId = archivoActividadRepository.findById(id);

        if (actividadId.isPresent()) {
            ArchivoActividadEntity archivoActividadEntity = actividadId.get();
            archivoActividadEntity.setStatus(object.getStatus());
            archivoActividadRepository.save(archivoActividadEntity);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public List<ArchivoDTO> byIdActividadAndArchivoNull(Long idActividad) {
        try {
            Optional<ActividadEntity> actividad = actividadRepository.findById(idActividad);
            ActividadEntity actividad2 = actividad.get();
            Sort sort = Sort.by("id");
            List<ArchivoActividadEntity> consulta = archivoActividadRepository
                    .findByIdActividadAndIdArchivoActividadAndStatus(actividad2, null, true, sort);
            List<ArchivoDTO> archivoDTO = new ArrayList<>(); 
            for(int i=0; i<consulta.size(); i++){
                ArchivoDTO archivoDTO2 = new ArchivoDTO();
                archivoDTO2.convertDto(consulta.get(i));
                Optional<ArchivoActividadEntity> archivo = archivoActividadRepository.findById(consulta.get(i).getId());
                ArchivoActividadEntity archivo2 = archivo.get();
                List<ArchivoActividadEntity> consulta2 = archivoActividadRepository
                .findByIdActividadAndIdArchivoActividadAndStatus(actividad2, archivo2, true, sort);
                for(int j=0; j<consulta2.size(); j++){
                    ArchivoDTO archivoDTO3 = new ArchivoDTO();
                    archivoDTO3.convertDto(consulta2.get(j));
                    archivoDTO2.agregarItemLista(archivoDTO3);
                }
                archivoDTO.add(archivoDTO2);
            }
            return archivoDTO;
        } catch (Exception e) {
            return null;
        }
    }
}
