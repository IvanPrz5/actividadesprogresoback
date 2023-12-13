package com.example.actividadesProgreso.Service.Formatos.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Actividades.ActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.UsarActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.UsuarioActividadEntity;
import com.example.actividadesProgreso.Repository.Actividades.ActividadRepository;
import com.example.actividadesProgreso.Repository.Actividades.UsarActividadRepository;
import com.example.actividadesProgreso.Repository.Actividades.UsuarioActividadRepository;

@Service
public class FormatosUtils {

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    UsuarioActividadRepository usuarioActividadRepository;

    @Autowired
    UsarActividadRepository usarActividadRepository;

    public Optional<ActividadEntity> getDataByActividad(Long idActividad){
        try {
            Optional<ActividadEntity> actividad = actividadRepository.findById(idActividad);
            return actividad;
        } catch (Exception e) {
            return null;
        }
    }

    
    public List<UsuarioActividadEntity> getParticipantesByActividad(Long idActividad){
        try {
            Optional<ActividadEntity> actividadEntity = actividadRepository.findById(idActividad);
            ActividadEntity actividadId = actividadEntity.get();
            List<UsuarioActividadEntity> usuarioActividad = usuarioActividadRepository.findByIdActividadAndUserActStatus(actividadId, true);
            return usuarioActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public List<UsarActividadEntity> getRecursosByActividad(Long idActividad){
        try {
            Optional<ActividadEntity> actividadEntity = actividadRepository.findById(idActividad);
            ActividadEntity actividadId = actividadEntity.get();
            List<UsarActividadEntity> usarActividad = usarActividadRepository.findByIdActividadAndStatus(actividadId, true);
            return usarActividad;
        } catch (Exception e) {
            return null;
        }
    }

    public String formatFechaSinHora(String fecha){
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            return new SimpleDateFormat("dd-MM-yyyy").format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public String formatFecha(String fecha){
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(fecha);
            return new SimpleDateFormat("dd-MM-yyyy, HH:mm").format(date);
        } catch (Exception e) {
            return null;
        }
    }
}