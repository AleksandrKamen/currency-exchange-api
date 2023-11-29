package validator.currency;

import dto.currency.CreateCurrencyDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import validator.Error;
import validator.ValidationResult;
import validator.Validator;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCurrencyValidator implements Validator<CreateCurrencyDto> {
    private static final CreateCurrencyValidator INSTANCE = new CreateCurrencyValidator();
    @Override
    public ValidationResult isValid(CreateCurrencyDto object) {
      ValidationResult validationResult = new ValidationResult();

      if (object.getName() == null){
          validationResult.add(Error.of(SC_BAD_REQUEST,"Параметр name отсутствует"));
      } else if (!object.getName().matches("[a-zA-Z ]*")){
          validationResult.add(Error.of(SC_BAD_REQUEST,"Параметр name содержит недопустимые символы"));
      }

      if (object.getCode() == null){
          validationResult.add(Error.of(SC_BAD_REQUEST,"Параметр code отсутствует"));
      } else if (!object.getCode().matches("[a-zA-Z]{3}")){
          validationResult.add(Error.of(SC_BAD_REQUEST,"Параметр code должен соответствовать стандарту ISO 4217"));
      }

      if (object.getSign() == null){
          validationResult.add(Error.of(SC_BAD_REQUEST,"Параметр sign отсутствует"));
      } else if(!object.getSign().matches("[^\\d\s]{1,3}")){
          validationResult.add(Error.of(SC_BAD_REQUEST,"Параметр sign содержит недопустимые символы или значение более 3 символов)"));
      }
      return validationResult;
    }
    public static CreateCurrencyValidator getInstance(){return INSTANCE;}
}
