package com.elixr.poc.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessagesUtil {

    private static final String MESSAGE_PROPERTY_FILE_PREFIX = "messages";
    private static ResourceBundle moduleMessagesResourceBundle;

    static{
        try{
            // Initialize messagesResourceBundle in a static block because the module may not have messages.properties.
            moduleMessagesResourceBundle = ResourceBundle.getBundle(MESSAGE_PROPERTY_FILE_PREFIX);
        }
        catch (MissingResourceException e){
            moduleMessagesResourceBundle = null;
        }
    }

    public static String getMessage(String key, Object ... arguments){

        String message = null;
        if(moduleMessagesResourceBundle != null){
            message = getMessage(moduleMessagesResourceBundle, key, arguments);
            if(!message.equals(key)){
                return message;
            }
        }
        return key;
    }

    public static String getMessage(ResourceBundle resourceBundle, String key, Object ... arguments){

        if(resourceBundle.containsKey(key)){
            String error = resourceBundle.getString(key);
            return MessageFormat.format(error, arguments);
        }

        log.debug("Key not found in ResourceBundle. baseName={}", resourceBundle.getBaseBundleName());
        return key;
    }
}
