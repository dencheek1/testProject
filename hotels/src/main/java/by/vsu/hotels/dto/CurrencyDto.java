package by.vsu.hotels.dto;

import by.vsu.hotels.model.CurrencyType;
import lombok.Data;

@Data
public class CurrencyDto {
    private String name;

    public CurrencyType toCurrencyType(){
        CurrencyType currencyType = new CurrencyType();
        currencyType.setName(name);
        return currencyType;
    }

    public static CurrencyDto fromCurrencyType(CurrencyType currencyType){
        if(currencyType == null) {
            return null;
        }
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setName(currencyType.getName());
        return currencyDto;
    }
}
