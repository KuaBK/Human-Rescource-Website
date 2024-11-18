//package CNPM.G14.ems.mapper;
//
//import CNPM.G14.ems.dto.request.PersonelCreationRequest;
//import CNPM.G14.ems.dto.request.PersonelUpdateRequest;
//import CNPM.G14.ems.dto.response.PersonelResponse;
//import CNPM.G14.ems.entity.Personel;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//
//@Component
//@Primary
//public class PersonelMapperImplement implements PersonelMapper{
//
//    @Override
//    public Personel toPersonel(PersonelCreationRequest request){
//        if(request == null){ return null; }
//        Personel personel = new Personel();
//        personel.setFirstName(request.getFirstName());
//        personel.setLastName(request.getLastName());
//        personel.setPosition(request.getPosition());
//        personel.setSex(request.getSex());
//        personel.setEmail(request.getEmail());
//        personel.setCity(request.getCity());
//        personel.setStreet(request.getStreet());
//        personel.setPhone(request.getPhoneNumber());
//        return personel;
//    }
//
//    @Override
//    public PersonelResponse toPersonelResponse(Personel personel){
//        if(personel == null){ return null; }
//        PersonelResponse response = new PersonelResponse();
//        response.setPersonelCode(personel.getPersonelCode());
//        response.setPosition(personel.getPosition());
//        response.setFirstName(personel.getFirstName());
//        response.setLastName(personel.getLastName());
//        response.setSex(personel.getSex());  // Set these values in the response object
//        response.setEmail(personel.getEmail());
//        response.setCity(personel.getCity());
//        response.setStreet(personel.getStreet());
//        response.setPhone(personel.getPhone());  // Set these values in the response object
//        return response;
//    }
//
//    @Override
//    public PersonelResponse updatePersonel(Personel personel, PersonelUpdateRequest request){
//        if(personel == null || request == null){ return null; }
//        personel.setFirstName(request.getFirstName());
//        personel.setLastName(request.getLastName());
//        personel.setSex(request.getSex());
//        personel.setEmail(request.getEmail());
//        personel.setCity(request.getCity());
//        personel.setStreet(request.getStreet());
//        personel.setPhone(request.getPhoneNumber());
//        return toPersonelResponse(personel);
//    }
//}
