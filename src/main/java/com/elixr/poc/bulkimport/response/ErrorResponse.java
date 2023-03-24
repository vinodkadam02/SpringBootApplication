package com.elixr.poc.bulkimport.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class ErrorResponse {
    private List<String> errorList = new ArrayList<>();

    public void addErrors(String errorMessage){
        errorList.add(errorMessage);
    }

    public boolean hasErrors(){
        if(errorList.size()>0){
            return true;
        }return false;
    }
}
