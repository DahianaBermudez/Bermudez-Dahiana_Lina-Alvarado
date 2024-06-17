package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDTO;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.service.IPacienteService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {


    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<PacienteSalidaDTO> registrarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto){
        return new ResponseEntity<>(pacienteService.registrarPaciente(pacienteEntradaDto), HttpStatus.CREATED);
    }


    //GET
    @GetMapping("/listar")
    public ResponseEntity<List<PacienteSalidaDTO>> listarPacientes(){
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")//localhost:8080/pacientes/x
    public ResponseEntity<PacienteSalidaDTO> buscarPacientePorId(@PathVariable Long id){
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }

    //PUT
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PacienteSalidaDTO> actualizarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto, @PathVariable Long id){
        return new ResponseEntity<>(pacienteService.actualizarPaciente(pacienteEntradaDto, id), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/eliminar")//localhost:8080/pacientes/eliminar?id=x
    public ResponseEntity<?> eliminarPaciente(@RequestParam Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }

}