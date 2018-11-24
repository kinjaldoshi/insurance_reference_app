/**
 * 
 */
package com.stg.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.insurance.model.ActiveTemplateTracker;

/**
 * @author Sagar dell pc
 *
 */
public interface ActiveTemplateRepository extends JpaRepository<ActiveTemplateTracker, Integer> {

}
