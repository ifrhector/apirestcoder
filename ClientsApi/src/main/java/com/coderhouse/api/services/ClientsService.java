package com.coderhouse.api.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.api.lib.DatesLib;
import com.coderhouse.api.models.ClientModel;
import com.coderhouse.api.repositories.IClientsRepository;
import com.coderhouse.api.vo.JsonResponse;

@Service
public class ClientsService {

	@Autowired
	IClientsRepository clientRepo;

	/**
	 * 
	 * @return JsonResponse from list clients with age and birthday in MX format
	 */
	public JsonResponse getAllClients() {
		JsonResponse response = new JsonResponse();

		DatesLib dLib = new DatesLib();

		List<Map<String, String>> finalData = new ArrayList<>();

		List<ClientModel> list = (ArrayList<ClientModel>) clientRepo.findAll();

		if (!list.isEmpty()) {

			for (ClientModel client : list) {

				Map<String, String> data = new HashMap<>();

				int age = dLib.getDateOfBirth(client.getBirthDay());

				data.put("Nombre: ", client.getFirstName());
				data.put("Apellido: ", client.getLastName());
				data.put("Email: ", client.getEmail());
				data.put("Nacimiento: ", dLib.getOnlyDate(client.getBirthDay()));
				data.put("Edad: ", age + "");

				finalData.add(data);
			}

			response.setData(finalData);
			response.setSuccess(true);
			response.setMessage("All data are ok!");
		}else {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("No data!");
		}

		return response;
	}

	/**
	 * 
	 * @param id
	 * @return client with age and birthday in MX format
	 */
	public JsonResponse getClientById(Long id) {
		Optional<ClientModel> client = this.clientRepo.findById(id);

		JsonResponse response = new JsonResponse();
		DatesLib dLib = new DatesLib();

		if (!client.isEmpty()) {

			Map<String, String> data = new HashMap<>();
			data.put("Nombre: ", client.get().getFirstName());
			data.put("Apellido: ", client.get().getLastName());
			data.put("Email: ", client.get().getEmail());
			data.put("Nacimiento: ", dLib.getOnlyDate(client.get().getBirthDay()));

			int age = dLib.getDateOfBirth(client.get().getBirthDay());

			data.put("Edad: ", age + "");

			response.setSuccess(true);
			response.setMessage("client found!");
			response.setData(data);
		} else {
			response.setSuccess(false);
			response.setMessage("client not found");
			response.setData(null);
		}

		return response;
	}

	/**
	 * Save a client in DB
	 * 
	 * @param client
	 * @return a client make on DB
	 */
	public ClientModel saveClient(ClientModel client) {
		return clientRepo.save(client);
	}

	public ClientModel updateById(Long id, ClientModel request) {
		try {
			if (clientRepo.existsById(id)) {
				request.setId(id);
				return clientRepo.save(request);
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return null;
	}

	/**
	 * Delete a client from DB
	 * 
	 * @param id
	 * @return boolean of process
	 */
	public Boolean deleteClientBy(Long id) {
		try {
			if (clientRepo.existsById(id)) {
				clientRepo.deleteById(id);
			}
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}

}
