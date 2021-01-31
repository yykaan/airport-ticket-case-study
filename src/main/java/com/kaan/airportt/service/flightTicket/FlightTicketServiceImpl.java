package com.kaan.airportt.service.flightTicket;

import com.kaan.airportt.entity.Flight;
import com.kaan.airportt.entity.FlightTicket;
import com.kaan.airportt.repository.FlightTicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightTicketServiceImpl implements FlightTicketService {

    private final FlightTicketRepository repository;

    public FlightTicketServiceImpl(FlightTicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public FlightTicket saveAndUpdate(FlightTicket t) {
        return repository.save(t);
    }

    @Override
    public Iterable<FlightTicket> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<FlightTicket> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Iterable<FlightTicket> saveAll(Iterable<FlightTicket> iterableList) {
        return repository.saveAll(iterableList);
    }

    @Override
    public Optional<FlightTicket> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Iterable<FlightTicket> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<FlightTicket> findAllById(Iterable<Long> idList) {
        return repository.findAllById(idList);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(FlightTicket t) {
        repository.delete(t);
    }

    @Override
    public void deleteAll(Iterable<FlightTicket> list) {
        repository.deleteAll(list);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<FlightTicket> findByFlight(Flight flight) {
        return repository.findByFlight(flight);
    }

    @Override
    public boolean isPurchased(Long ticketId) {
        FlightTicket flightTicket = repository.findByIdAndPurchasedTrue(ticketId);
        if (flightTicket == null){
            return false;
        }else {
            return true;
        }
    }
}
