package com.example.bookingtrain.service;

import com.example.bookingtrain.model.Booking;
import com.example.bookingtrain.repository.BookingRepository;
import com.example.bookingtrain.service.inter.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements IBookingService {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking getById(int id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Page<Booking> getAll(Pageable page) {
        return bookingRepository.findAll(page);
    }

    public Page<Booking> searchByPassengerName(String passenger, Pageable page){
        return  bookingRepository.findByUserUsername(passenger, page);
    }

    @Override
    public Booking save(Booking book) {
        return bookingRepository.save(book);
    }

    @Override
    public boolean delete(int id) {
        Booking book = getById(id);
        if (book != null) {
            book.setStatusBooking(0);
            bookingRepository.save(book);
            return true;
        }
        return false;
    }

    public List<Booking> getByUserId(int userId) {
        return bookingRepository.findByUserId(userId);
    }
}