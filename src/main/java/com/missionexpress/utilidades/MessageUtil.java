/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.missionexpress.utilidades;

import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 *
 * @author amendez
 */
final public class MessageUtil {

    private MessageUtil() {

    }

    /**
     * Objetivo: Adicionar mensaje Descripcion: Metodo que lleva a cabo la
     * accion de adicionar/agregar mensaje determinado
     *
     * @author amendez
     * @param severity
     * @param summary
     * @param clientId
     */
    public static void addMessage(final Severity severity,
            final String summary, final String clientId) {
        final FacesContext context = FacesContext.getCurrentInstance();
        final FacesMessage facesMsg
                = new FacesMessage(severity, summary, null);
        context.addMessage(clientId, facesMsg);
    }

    /**
     * Objetivo: Representar mensajes Descripcion: Metodo por medio del cual se
     * representan los niveles de severidad de los mensajes
     *
     * @author amendez
     * @param message
     * @param severity
     */
    private static void addMessageWithSeverity(final String summary, final String detail,
            final Severity severity) {

        final FacesContext context = FacesContext.getCurrentInstance();
        final FacesMessage facesMsg
                = new FacesMessage(severity, summary, detail);
        context.addMessage(null, facesMsg);
        context.getExternalContext().getFlash().setKeepMessages(Boolean.TRUE);
    }

    /**
     * Objetivo: Representar mensaje Descripcion: Metodo por el cual se
     * representa el mensaje de advertencia
     *
     * @author amendez
     * @param summary
     * @param detail
     */
    public static void addMessageWarning(final String summary, final String detail) {
        addMessageWithSeverity(summary, detail, FacesMessage.SEVERITY_WARN);
    }

    /**
     * Objetivo: Representar mensaje Descripcion: Metodo por el cual se
     * representa el mensaje informativo
     *
     * @author amendez
     * @param summary
     * @param detail
     */
    public static void addMessageInfo(final String summary, final String detail) {
        addMessageWithSeverity(summary, detail, FacesMessage.SEVERITY_INFO);
    }

    /**
     * Objetivo: Representar mensaje Descripcion: Metodo por el cual se
     * representa el mensaje de error
     *
     * @author amendez
     * @param summary
     * @param detail
     */
    public static void addMessageError(final String summary, final String detail) {
        addMessageWithSeverity(summary, detail, FacesMessage.SEVERITY_ERROR);
    }

    /**
     * Objetivo: Representar mensaje Descripcion: Metodo por el cual se
     * representa el mensajes de errores fatales
     *
     * @author amendez
     * @param summary
     * @param detail
     */
    public static void addMessageFatal(final String summary, final String detail) {
        addMessageWithSeverity(summary, detail, FacesMessage.SEVERITY_FATAL);
    }

    /**
     * Objetivo: Verificar mensaje Descripcion: Metodo por el cual se verifica
     * la existencia de mensajes
     *
     * @author amendez
     * @return static boolean
     */
    public static boolean hasMessages() {
        final List<FacesMessage> facesMessages
                = FacesContext.getCurrentInstance().getMessageList();
        return !facesMessages.isEmpty();
    }

    /**
     * Objetivo: Verificar mensaje Descripcion: Metodo por el cual se valida si
     * el mensaje presneta errores
     *
     * @author amendez
     * @return static boolean
     */
    public static boolean hasWarnOrHigherMessage() {
        boolean hasError = Boolean.FALSE;
        final FacesContext context = FacesContext.getCurrentInstance();
        final List<FacesMessage> msgList = context.getMessageList();
        for (final FacesMessage facesMessage : msgList) {
            if (facesMessage.getSeverity() == FacesMessage.SEVERITY_WARN
                    || facesMessage.getSeverity() == FacesMessage.SEVERITY_ERROR
                    || facesMessage.getSeverity() == FacesMessage.SEVERITY_FATAL) {
                hasError = Boolean.TRUE;
                break;
            }
        }

        return hasError;
    }

    /**
     * Objetivo: Limpiar mensaje Descripcion: Metodo por el cual se limpian los
     * mensajes en contexto
     *
     * @author amendez
     * @param severity
     */
    public static void clearMessages(final Severity severity) {
        final Iterator<FacesMessage> iterable
                = FacesContext.getCurrentInstance().getMessages();
        while (iterable.hasNext()) {
            final FacesMessage facesMessage = iterable.next();
            if (severity == null || severity.getOrdinal()
                    == facesMessage.getSeverity().getOrdinal()) {
                iterable.remove();
            }
        }
    }

}
