package lk.ijse.util;

import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.Reservation;
import lk.ijse.entity.Room;
import lk.ijse.entity.Student;
import lk.ijse.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DTOtoEntity {
        private static DTOtoEntity dtOtoEntity;

        private DTOtoEntity() {

        }

        public static DTOtoEntity getInstance() {
            return dtOtoEntity == null ? dtOtoEntity = new DTOtoEntity() : dtOtoEntity;
        }

        public UserDTO toUserDto(User entity) {
            return new UserDTO( entity.getUser_name(), entity.getPassword(), entity.getEmail());
        }

        public User toUserEntity(UserDTO dto) {
            return new User( dto.getUser_name(), dto.getPassword(), dto.getEmail());
        }

        public StudentDTO toStudentDto(Student student) {
            StudentDTO studentDto = new StudentDTO();
            studentDto.setStudent_id(student.getStudent_id());
            studentDto.setName(student.getName());
            studentDto.setContact_no(student.getContact_no());
            studentDto.setDob(student.getDob());
            studentDto.setGender(student.getGender());
            studentDto.setAddress(student.getAddress());
            studentDto.setEmail(student.getEmail());
            studentDto.setUniversity(student.getUniversity());
            return studentDto;
        }

        public Student toStudentEntity(StudentDTO dto) {
            System.out.println(dto);
            Student studentEntity = new Student();
            studentEntity.setStudent_id(dto.getStudent_id());
            studentEntity.setName(dto.getName());
            studentEntity.setContact_no(dto.getContact_no());
            studentEntity.setDob(dto.getDob());
            studentEntity.setGender(dto.getGender());
            studentEntity.setAddress(dto.getAddress());
            studentEntity.setEmail(dto.getEmail());
            studentEntity.setUniversity(dto.getUniversity());
            return studentEntity;
        }

        public RoomDTO toRoomDto(Room entity) {
            RoomDTO dto = new RoomDTO();
            dto.setRoom_type_id(entity.getRoom_type_id());
            dto.setType(entity.getType());
            dto.setKey_money(entity.getKey_money());
            dto.setQty(entity.getQty());

            return dto;
        }

        public Room toRoomEntity(RoomDTO dto) {
            Room entity = new Room();
            entity.setRoom_type_id(dto.getRoom_type_id());
            entity.setType(dto.getType());
            entity.setKey_money(dto.getKey_money());
            entity.setQty(dto.getQty());
//            entity.setReservations(convertDTOToEntity(dto.getReservations()));
            return entity;
        }

        public Reservation toReservationEntity(ReservationDTO dto) {
            System.out.println(dto);
            Reservation entity = new Reservation();
            entity.setRes_id(dto.getRes_id());
            entity.setStudent(toStudentEntity(dto.getStudent()));
            entity.setRoom(toRoomEntity(dto.getRoom()));
            entity.setDate(dto.getDate());
            entity.setStatus(dto.getStatus());
            return entity;
        }

        public ReservationDTO toReservationDto(Reservation entity) {
            ReservationDTO dto = new ReservationDTO();
            dto.setRes_id(entity.getRes_id());
            dto.setStudent(toStudentDto(entity.getStudent()));
            dto.setRoom(toRoomDto(entity.getRoom()));
            dto.setDate(entity.getDate());
            dto.setStatus(entity.getStatus());
            return dto;
        }

    public static List<Reservation> convertDTOToEntity(List<ReservationDTO> reservationDTOS) {
        List<Reservation> reservations = new ArrayList<>();

        // Iterate through the ArrayList of DTOs and convert each DTO to an entity
        for (ReservationDTO reservationDTO : reservationDTOS) {
            Reservation reservation = DTOtoEntity.getInstance().toReservationEntity(reservationDTO);

            reservations.add(reservation);
        }

        return reservations;
    }


}
