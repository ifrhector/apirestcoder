package com.coderhouse.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.api.models.ClientModel;
import com.coderhouse.api.services.ClientsService;
import com.coderhouse.api.vo.JsonResponse;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientsService clientsService;
	

	/**
	 * 
	 * @return list of clients with age
	 */
	@GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public JsonResponse getAllClients() {
		return this.clientsService.getAllClients();
	}

	/**
	 * 
	 * @param id
	 * @return JsonResponse of client found or not found
	 */
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public JsonResponse getById(@PathVariable Long id) {
		return this.clientsService.getClientById(id);
	}

	/**
	 * Add new client
	 * @param client
	 * @return client added
	 */
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ClientModel saveClient(@RequestBody ClientModel client) {
		return this.clientsService.saveClient(client);
	}

	/**
	 * Update data from client
	 * @param request, object client
	 * @param id
	 * @return a client updated
	 */
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientModel> updateUser(@RequestBody ClientModel request, @PathVariable Long id) {
		ClientModel updatedClient = clientsService.updateById(id, request);

		if (updatedClient != null) {
			return new ResponseEntity<>(updatedClient, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete client by id
	 * @param id
	 * @return delete or not client
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		Boolean deleted = clientsService.deleteClientBy(id);

		if (deleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
