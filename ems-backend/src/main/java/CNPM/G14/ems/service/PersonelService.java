package CNPM.G14.ems.service;

import CNPM.G14.ems.dto.request.PersonnelCreationRequest;
import CNPM.G14.ems.dto.request.PersonnelUpdateRequest;
import CNPM.G14.ems.dto.response.PersonelResponse;

import java.util.List;

public interface PersonelService {
    PersonelResponse createPersonel(PersonnelCreationRequest request);
    PersonelResponse updatePersonel(int code, PersonnelUpdateRequest request);
    void deletePersonel(int code);
    PersonelResponse getPersonel(int code);
    List<PersonelResponse> getAllPersonel();
}
