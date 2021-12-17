/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.missionexpress.bean;

import com.missionexpress.model.DatosBasicos;
import com.missionexpress.model.Imagen;
import com.missionexpress.utilidades.Constant;
import com.missionexpress.utilidades.MessageUtil;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
import org.omnifaces.cdi.GraphicImageBean;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author dohko
 */
@Named
@GraphicImageBean
public class ImageBean implements Serializable {

    private final static Logger LOG = Logger.getLogger(ImageBean.class.getName());

    private List<Imagen> listaImagenes;
    private DatosBasicos datosBasicos;
    private Integer index;
    private Integer width;
    private Integer height;

    /**
     * Creates a new instance of ImageBean
     */
    public ImageBean() {
        super();
    }

    @PostConstruct
    public void init() {
        listaImagenes = new ArrayList<>();
        datosBasicos = new DatosBasicos();
    }

    public void uploadImage(final FileUploadEvent imagen) {
        try {
            final Imagen file = new Imagen();
            file.setFileName(imagen.getFile().getFileName());
            file.setContentType(imagen.getFile().getContentType());
            file.setInputStream(imagen.getFile().getInputStream());
            file.setSize(imagen.getFile().getSize() / 1024);
            final byte[] imagenBytes = imagen.getFile().getContent();
            final BufferedImage bufferedImage
                    = ImageIO.read(new ByteArrayInputStream(imagenBytes));
            file.setContent(imagenBytes);
            file.setHeight(bufferedImage.getHeight());
            file.setWidth(bufferedImage.getWidth());
            listaImagenes.add(file);
            LOG.log(Level.INFO, "Se ha guardado la imagen "
                    .concat(file.getFileName()));
        } catch (final IOException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
    }

    public void save() {
        this.listaImagenes.get(this.index)
                .setDatosBasicos(this.datosBasicos);
        MessageUtil.addMessageInfo("Satisfactorio",
                "Los datos se han guardado correctamente");
        PrimeFaces.current().executeScript("PF('imageDetail').hide()");
    }

    public void preEdit(final int index, final DatosBasicos basicos) {
        this.index = index;
        if (basicos == null) {
            this.datosBasicos = new DatosBasicos();
            datosBasicos.setDate(new Date());
            datosBasicos.setHourInternational(new Date());
            datosBasicos.setHourLocal(new Date());
        } else {
            this.datosBasicos = basicos;
        }

    }

    /**
     *
     * @param index
     * @return
     */
    public byte[] imagenPrevious(final Integer index) {
        return listaImagenes.get(index).getContent();
    }

    public byte[] imagenComplete() {
        if (this.index == null) {
            return getImageNotFound();
        }
        return listaImagenes.get(getIndex()).getContent();
    }

    private byte[] getImageNotFound() {
        try {
            return IOUtils.toByteArray(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getResourceAsStream(Constant.PATH_IMAGE_NOT_FOUND));
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    public void downloadImage() {
        if (this.index == null) {
            MessageUtil.addMessageError("UPS!",
                    "No se ha encontrado ninguna imagen para descargar");
        } else {
            final Imagen imagen = listaImagenes.get(this.index);
            final StringBuilder builder = new StringBuilder();
            builder.append("loadHtmlToImage('").append(imagen
                    .getContentType()).append("'");
            builder.append(Constant.COMMA).append("'")
                    .append(imagen.getFileName()).append("')");
            PrimeFaces.current().executeScript(builder.toString());
        }

    }

    public void preConfigImageComplete(final int index) {
        this.index = index;
        final Imagen image = this.listaImagenes.get(index);
        if (image.getDatosBasicos() != null) {
            this.datosBasicos = image.getDatosBasicos();
        } else {
            this.datosBasicos = new DatosBasicos();
        }
        this.width = this.listaImagenes.get(index).getWidth();
        this.height = this.listaImagenes.get(index).getHeight();
    }

    public String convertDateLocalString() {
        final Date date = this.datosBasicos.getHourLocal();
        if (date == null) {
            return Constant.EMPTY;
        } else {
            final StringBuilder builder = new StringBuilder("LOCAL ");
            builder.append(date.getHours() <= Constant.TEN ? "0" + date.getHours()
                    : date.getHours()).append(Constant.TWO_POINT);
            builder.append(date.getMinutes() <= Constant.TEN ? "0"
                    + date.getMinutes() : date.getMinutes())
                    .append(Constant.TWO_POINT);
            builder.append(date.getSeconds()
                    <= Constant.TEN ? "0"
                            + date.getSeconds() : date.getSeconds());
            return builder.toString();
        }
    }

    public String convertDateInternationalString() {
        final Date date = this.datosBasicos.getHourInternational();
        if (date == null) {
            return Constant.EMPTY;
        } else {
            final StringBuilder builder = new StringBuilder("GMT ");
            builder.append(date.getHours() <= Constant.TEN ? "0" + date.getHours()
                    : date.getHours()).append(Constant.TWO_POINT);
            builder.append(date.getMinutes() <= Constant.TEN ? "0"
                    + date.getMinutes() : date.getMinutes())
                    .append(Constant.TWO_POINT);
            builder.append(date.getSeconds()
                    <= Constant.TEN ? "0"
                            + date.getSeconds() : date.getSeconds());
            return builder.toString();
        }
    }

    public String convertDateToString() {
        final Date date = this.datosBasicos.getDate();
        if (date == null) {
            return Constant.EMPTY;
        } else {
            final Locale loc = new Locale("en", "CO");
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MM.dd.yyyy", loc);
            return formatter.format(date).toUpperCase()
                    .replace(Constant.COMMA, Constant.EMPTY);
        }
    }

    /**
     * @return the listaImagenes
     */
    public List<Imagen> getListaImagenes() {
        return listaImagenes;
    }

    /**
     * @param listaImagenes the listaImagenes to set
     */
    public void setListaImagenes(List<Imagen> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @return the datosBasicos
     */
    public DatosBasicos getDatosBasicos() {
        return datosBasicos;
    }

    /**
     * @param datosBasicos the datosBasicos to set
     */
    public void setDatosBasicos(DatosBasicos datosBasicos) {
        this.datosBasicos = datosBasicos;
    }

    /**
     * @return the width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

}
