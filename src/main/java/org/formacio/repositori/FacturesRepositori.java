package org.formacio.repositori;

import java.util.List;

import org.formacio.domain.Factura;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FacturesRepositori extends CrudRepository<Factura, Long>{

	@Query("select sum(linies.total) from Factura factura join factura.linies linies where factura.client.nom = :nomClient")
	public Number totalClient(@Param("nomClient") String client);

	@Query("select factura from Factura factura where factura.client.nom = :nomClient")
	public List<Factura> obteFactures(@Param("nomClient") String nomClient);
	
}
