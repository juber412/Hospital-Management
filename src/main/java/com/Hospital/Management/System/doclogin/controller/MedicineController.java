package com.Hospital.Management.System.doclogin.controller;


import com.Hospital.Management.System.doclogin.entity.Medicine;
import com.Hospital.Management.System.doclogin.repository.MedicineRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v3/medicines")
public class MedicineController {

    private MedicineRepository medicineRepository;

    public MedicineController(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @PostMapping("/insert")
    public Medicine createMedicine(@RequestBody Medicine medicine)
    {
        return medicineRepository.save(medicine);
    }

    @GetMapping
    public List<Medicine> getAllMedicine()
    {
        return medicineRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable long id) throws AttributeNotFoundException {
        Medicine medicine =  medicineRepository.findById(id).orElseThrow(()->new AttributeNotFoundException("The Medicine id "+id+" Not Found"));

        return ResponseEntity.ok(medicine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable long id, @RequestBody Medicine medicine) throws AttributeNotFoundException {
        Medicine medicineDetails = medicineRepository.findById(id).orElseThrow(()->new AttributeNotFoundException("The Medicine id "+id+" Not Found"));

        medicineDetails.setDrugName(medicine.getDrugName());
        medicineDetails.setStock(medicine.getStock());

        Medicine upadatedMedicineDetails =  medicineRepository.save(medicineDetails);
        return ResponseEntity.ok(upadatedMedicineDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteMedicineById(@PathVariable long id) throws AttributeNotFoundException {
        Medicine medicine = medicineRepository.findById(id).orElseThrow(()->new AttributeNotFoundException("The Medicine id "+id+" Not Found"));

        medicineRepository.delete(medicine);

        Map<String,Boolean> response =  new HashMap<String,Boolean>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
     }
}
