package com.example.woc.component;

import org.springframework.data.repository.core.CrudMethods;

import javax.validation.groups.Default;

/**
 * @author 慕北_Innocent
 * @version 1.0
 * @date 2022/2/13 18:23
 */

public interface ValidGroup extends Default {

    interface usernameCheck {
    }

    interface passwordCheck {
    }

    interface emailCheck {
    }

}
