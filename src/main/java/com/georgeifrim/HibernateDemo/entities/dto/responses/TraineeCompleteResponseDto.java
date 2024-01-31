package com.georgeifrim.HibernateDemo.entities.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class TraineeCompleteResponseDto{

    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String address;
    boolean isActive;
//    @JsonIgnoreProperties("traineeList")
    List<TrainerCompleteResponseDto> trainerList;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<TrainerCompleteResponseDto> getTrainerList() {
        return trainerList;
    }

    public void setTrainerList(List<TrainerCompleteResponseDto> trainerList) {
        this.trainerList = trainerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TraineeCompleteResponseDto that)) return false;
        return isActive() == that.isActive() && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getDateOfBirth(), that.getDateOfBirth()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getTrainerList(), that.getTrainerList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getDateOfBirth(), getAddress(), isActive(), getTrainerList());
    }

    @Override
    public String toString() {
        return "TraineeCompleteResponseDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", isActive=" + isActive +
                ", trainerList=" + trainerList +
                '}';
    }
}
