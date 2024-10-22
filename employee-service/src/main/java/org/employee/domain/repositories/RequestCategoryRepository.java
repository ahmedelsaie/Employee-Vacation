package org.employee.domain.repositories;

import org.employee.domain.model.RequestCategory;
import org.employee.domain.model.RequestCategoryType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestCategoryRepository extends CrudRepository<RequestCategory, Long> {

    public RequestCategory findOneByRequestCategoryType(RequestCategoryType requestCategoryType);
}
