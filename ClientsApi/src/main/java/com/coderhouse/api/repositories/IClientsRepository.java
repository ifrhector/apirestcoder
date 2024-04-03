package com.coderhouse.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coderhouse.api.models.ClientModel;

public interface IClientsRepository extends JpaRepository<ClientModel, Long> {

	
}
