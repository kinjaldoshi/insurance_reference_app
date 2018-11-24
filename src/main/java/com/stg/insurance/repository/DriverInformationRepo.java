/**
 * 
 */
package com.stg.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.insurance.model.DriversInformation;

/**
 * @author abhinavkumar.gupta
 *
 */
public interface DriverInformationRepo extends JpaRepository<DriversInformation, Integer> {

}
