package com.example.actividadesProgreso.Service.Formatos;

import org.springframework.http.ContentDisposition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.actividadesProgreso.Entity.Actividades.ActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.UsarActividadEntity;
import com.example.actividadesProgreso.Entity.Actividades.UsuarioActividadEntity;
import com.example.actividadesProgreso.Repository.Actividades.ActividadRepository;
import com.example.actividadesProgreso.Service.Formatos.Utils.FormatosUtils;
import com.example.actividadesProgreso.Service.Formatos.Utils.FormatosData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class FormatosService {

    @Autowired
    FormatosUtils formatosUtils;

    @Autowired
    ActividadRepository actividadRepository;

    public ResponseEntity<Resource> jasperToPDF(Long idActividad) {
        
        try {
            Map<String, Object> parametros = new HashMap<>();
            InputStream inputStreamJasper = getFileFromResourceAsStream("jasper/jasperFormat.jrxml");
            JasperReport jasper = JasperCompileManager.compileReport(inputStreamJasper);
            
            Optional<ActividadEntity> actividad = formatosUtils.getDataByActividad(idActividad);
            List<UsuarioActividadEntity> usuariosActividad = formatosUtils.getParticipantesByActividad(idActividad);
            List<UsarActividadEntity> usarActividad = formatosUtils.getRecursosByActividad(idActividad);

            String nombreCompleto = actividad.get().getIdUsuarioAsigno().getNombre().toUpperCase() + " "
                    + actividad.get().getIdUsuarioAsigno().getApPaterno().toUpperCase();

            parametros.put("colaborador", nombreCompleto);
            parametros.put("tipoActividad", actividad.get().getIdTipoActividad().getDescripcion());
            parametros.put("horaSalida", formatosUtils.formatFecha(actividad.get().getFechaInicio().toString()));
            parametros.put("horaFin", formatosUtils.formatFecha(actividad.get().getFechaFin().toString()));
            parametros.put("fecha", formatosUtils.formatFechaSinHora(actividad.get().getFechaInicio().toString()));
            parametros.put("descripcionActividad", actividad.get().getDescripcion().toUpperCase());
            parametros.put("cliente", actividad.get().getIdClientes().getDescripcion());

            List<FormatosData> conceptoList = new ArrayList<>();
            String participantes = "";
            for (int i = 0; i < usuariosActividad.size(); i++) {
                participantes = participantes + usuariosActividad.get(i).getIdUsuario().getNombre().toUpperCase() + " "
                        +
                        usuariosActividad.get(i).getIdUsuario().getApPaterno().toUpperCase() + ", ";
            }
            parametros.put("participantes", participantes);

            String materiales = "";
            List<String> tipo = new ArrayList<>();
            for (int i = 0; i < usarActividad.size(); i++) {
                tipo.add(usarActividad.get(i).getIdBienActividad().getTipo());
                materiales = materiales + usarActividad.get(i).getIdBienActividad().getDescripcion().toUpperCase()
                        + ", ";
            }
            if(buscaTipo(tipo)){
                parametros.put("transporte", "PRIVADO");
            }else{
                parametros.put("transporte", "PÚBLICO");
            }

            parametros.put("materiales", materiales);

            FormatosData algo = new FormatosData("", "");
            conceptoList.add(algo);

            JRBeanCollectionDataSource conceptosDataSource = new JRBeanCollectionDataSource(conceptoList);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parametros, conceptosDataSource);

            byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
            String sdf = (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
            StringBuilder stringBuilder = new StringBuilder().append("InvoicePDF:");

            ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                    .filename(stringBuilder.append("ARCHIVO")
                            .append("generateDate:")
                            .append(sdf)
                            .append(".pdf")
                            .toString())
                    .build();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(contentDisposition);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .headers(headers).body(new ByteArrayResource(reporte));
        } catch (Exception e) {
            return null;
        }
    }

    private InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    public boolean buscaTipo(List<String> tipo){
        Boolean existe = tipo.contains("transporte");
        if(existe){
            return true;
        }else{
            return false;
        }
    }
}