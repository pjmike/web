package com.pjmike.project.service;

import com.pjmike.project.domain.Contact;

/**
 * @author pjmike
 * @create 2018-05-10 21:41
 */
public interface IndexAndCantactService {
    /**
     * 返回内页
     *
     * @return
     */
    Contact findCantact();

    /**
     * 修改内页
     *
     * @param contact
     * @return
     */
    Contact update(Contact contact);


}
