package CNPM.G14.ems.service.impl;

import CNPM.G14.ems.dto.request.PersonnelCreationRequest;
import CNPM.G14.ems.dto.request.PersonnelUpdateRequest;
import CNPM.G14.ems.dto.response.PersonelResponse;
import CNPM.G14.ems.entity.Account;
import CNPM.G14.ems.entity.Employee;
import CNPM.G14.ems.entity.Manager;
import CNPM.G14.ems.entity.Personnel;
import CNPM.G14.ems.exception.AppException;
import CNPM.G14.ems.exception.ErrorCode;
import CNPM.G14.ems.repository.AccountRepository;
import CNPM.G14.ems.repository.EmployeeRepository;
import CNPM.G14.ems.repository.ManagerRepository;
import CNPM.G14.ems.repository.PersonnelRepository;
import CNPM.G14.ems.service.PersonelService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonelServiceImpl implements PersonelService {

    PersonnelRepository personnelRepository;
    AccountRepository accountRepository;

    EmployeeRepository employeeRepository;
    ManagerRepository managerRepository;

    private Personnel toPersonel(PersonnelCreationRequest request){
        if (request == null){ return null; }
        Personnel personnel = new Personnel();
        personnel.setFirstName(request.getFirstName());
        personnel.setLastName(request.getLastName());
        personnel.setPosition(request.getPosition());
        personnel.setGender(request.getGender());
        personnel.setEmail(request.getEmail());
        personnel.setCity(request.getCity());
        personnel.setStreet(request.getStreet());
        personnel.setPhone(request.getPhoneNumber());
        return personnel;
    }

    private PersonelResponse toPersonelResponse(Personnel personnel){
        String username = personnel.getAccount().getUsername();
        String password = personnel.getAccount().getPassword();

        Optional<Employee> employee = employeeRepository.findByCode(personnel.getCode());
        Optional<Manager> manager = managerRepository.findByCode(personnel.getCode());

        int deptId = 0;
        String deptName = "";

        if(employee.isPresent()) {
            deptId = employee.get().getDepartment().getId();
            deptName = employee.get().getDepartment().getName();
        } else if (manager.isPresent() && manager.get().getDepartment() != null){
            deptId = manager.get().getDepartment().getId();
            deptName = manager.get().getDepartment().getName();
        }

        return new PersonelResponse(
                personnel.getCode(),
                personnel.getPosition(),
                personnel.getFirstName(),
                personnel.getLastName(),
                personnel.getGender(),
                personnel.getEmail(),
                username,
                password,
                personnel.getCity(),
                personnel.getStreet(),
                personnel.getPhone(),
                deptName,
                deptId
        );
    }

    private int generateUniquePersonelCode() {
        Random random = new Random();
        int code;
        do {
            code = 1000000 + random.nextInt(9000000);
        } while (personnelRepository.existsById(code));
        return code;
    }

    @Override
    public PersonelResponse createPersonel(PersonnelCreationRequest request){
        Personnel personnel = toPersonel(request);
        Account account = accountRepository.findAccountById(request.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account with ID " + request.getAccountId() + " does not exist."));
        personnel.setAccount(account);

        personnel.setCode(generateUniquePersonelCode());
        personnelRepository.save(personnel);

        return toPersonelResponse(personnel);
    }

    @Override
    public PersonelResponse updatePersonel(int code, PersonnelUpdateRequest request){
        Personnel personnel = personnelRepository.findByCode(code)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        personnel.setFirstName(request.getFirstName());
        personnel.setLastName(request.getLastName());
        personnel.setGender(request.getGender());
        personnel.setEmail(request.getEmail());
        personnel.setCity(request.getCity());
        personnel.setStreet(request.getStreet());
        personnel.setPhone(request.getPhoneNumber());

        return toPersonelResponse(personnelRepository.save(personnel));
    }

    @Override
    public PersonelResponse getPersonel(int code){
        return personnelRepository.findByCode(code)
                .map(this::toPersonelResponse)
                .orElseThrow(() -> new RuntimeException("Personel not found with code: " + code));
    }

    @Override
    public List<PersonelResponse> getAllPersonel(){
        return personnelRepository.findAll().stream()
                .map(this::toPersonelResponse) // Map each Personel to PersonelResponse
                .toList();
    }

    @Override
    public void deletePersonel(int code){
        Personnel personnel = personnelRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Personel not found with code: " + code));
        personnelRepository.delete(personnel);
    }
}

