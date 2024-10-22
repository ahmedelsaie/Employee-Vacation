package org.employee.domain.repositories;

import org.employee.domain.model.TimeOffRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RequestsRepository extends CrudRepository<TimeOffRequest, Long> {

    public List<TimeOffRequest> findAllByStartDateBetween(@Param("startDate") Date startDate,
                                                          @Param("endDate") Date endDate);

    public List<TimeOffRequest> findAllByEndDateBetween(@Param("startDate") Date startDate,
                                                        @Param("endDate") Date endDate);
}
