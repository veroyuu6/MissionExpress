/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.missionexpress.converter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.WeakHashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author dohko
 */
@FacesConverter(value = "genericConverter")
public class GenericConverter implements Converter {

    private static Map<Object, String> entities = new WeakHashMap<>();

    @Override
    public String getAsString(final FacesContext context, 
            final UIComponent component, final Object entity) {
        synchronized (entities) {
            if (entities.containsKey(entity)) {
                return entities.get(entity);
            } else {
                final String uuid = UUID.randomUUID().toString();
                entities.put(entity, uuid);
                return uuid;
            }
        }
    }

    @Override
    public Object getAsObject(final FacesContext context, 
            final UIComponent component, final String uuid) {
        for (final Entry<Object, String> entry : entities.entrySet()) {
            if (entry.getValue().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
