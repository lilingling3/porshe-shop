package com.boldseas.porscheshop.dtos;

import com.boldseas.porscheshop.common.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;

/**
 * @author fei.ye
 * @version 2018/5/9.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private Gender gender;
    private String email;
    private String province;
    private String provinceCode;
    private String city;
    private String cityCode;
    private String address;
    private Integer postalCode;

    /**
     * 是否有必要的字段
     *
     * @return
     */
    public Boolean hasNecessaryInfo(){
        return StringUtils.isNotEmpty(this.firstName) && StringUtils.isNotEmpty(this.lastName) && Objects.nonNull(this.gender);
    }

    public String getGenderValue() {
        return Objects.nonNull(this.gender) ? this.gender.getValue() : CommonConstants.EMPTY_STRING;
    }

}
