package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//                                                     <tipo da classe que será manipulada pelo repository, tipo do ID da classe manipulada>
public interface CargoRepository extends CrudRepository<Cargo,Integer> {

}
