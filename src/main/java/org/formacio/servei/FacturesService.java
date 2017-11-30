package org.formacio.servei;

import org.formacio.domain.Factura;
import org.formacio.domain.LiniaFactura;
import org.formacio.repositori.FacturesRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FacturesService {
	
	@Autowired
	FacturesRepositori facturesRepositori;
	
	@Autowired
	FidalitzacioService fidalitzacioService;
	
	/*
	 * Aquest metode ha de carregar la factura amb id idFactura i afegir una nova linia amb les dades
	 * passades (producte i totalProducte)
	 * 
	 * S'ha de retornar la factura modificada
	 * 
	 * Per implementar aquest metode necessitareu una referencia (dependencia) a FacturesRepositori
	 */
	public Factura afegirProducte (long idFactura, String producte, int totalProducte) {
		Factura factura = facturesRepositori.findById(idFactura).get();
		LiniaFactura linia = new LiniaFactura();
		linia.setProducte(producte);
		linia.setTotal(totalProducte);
		factura.getLinies().add(linia);
		if (factura.getLinies().size() > 3) fidalitzacioService.notificaRegal(factura.getClient().getEmail());
		return factura;
	}
}
