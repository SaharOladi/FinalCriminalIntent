package com.example.finalcriminalintent.repository;

import com.example.finalcriminalintent.model.Crime;

import java.util.List;
import java.util.UUID;

public interface IRepository {
    List<Crime> getCrimes();
    Crime getCrime(UUID crimeId);
    void insertCrime(Crime crime);
    void updateCrime(Crime crime);
    void deleteCrime(Crime crime);
    void removeAllCrime();
    int getPosition(UUID crimeId);
}
