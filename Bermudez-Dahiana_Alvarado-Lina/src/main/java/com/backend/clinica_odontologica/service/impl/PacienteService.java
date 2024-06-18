package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDTO;
import com.backend.clinica_odontologica.entity.Paciente;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.repository.PacienteRepository;
import com.backend.clinica_odontologica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//public class PacienteService implements IPacienteService {
//    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
//    private final PacienteRepository pacienteRepository;
//    private final ModelMapper modelMapper;
//    private IDao<Paciente> pacienteIDao;
//
//    public PacienteService(PacienteRepository pacienteRepository, IDao<Paciente> pacienteIDao, ModelMapper modelMapper) {
//        this.pacienteRepository = pacienteRepository;
//        this.pacienteIDao = pacienteIDao;
//        this.modelMapper = modelMapper;
//        configureMapping();
//    }
//
//
//    @Override
//    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto) {
//        //Logica de negocio
//        //Mapeo de dtos a entidad
//        LOGGER.info("PacienteEntradaDto: " + pacienteEntradaDto);
//        Paciente paciente = modelMapper.map(pacienteEntradaDto, Paciente.class);
//        LOGGER.info("PacienteEntidad: " + paciente);
//        Paciente pacienteRegistrado = pacienteIDao.registrar(paciente);
//        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteRegistrado, PacienteSalidaDto.class);
//        // mapeo entidad dto
//        LOGGER.info("PacienteSalida: " + pacienteSalidaDto);
//        return pacienteSalidaDto;
//    }
//
//    @Override
//    public List<PacienteSalidaDto> listarPacientes() {
//        List<PacienteSalidaDto> pacientes = pacienteIDao.listarTodos()
//                .stream()
//                .map(paciente -> modelMapper.map(paciente, PacienteSalidaDto.class))
//                .toList();
//        LOGGER.info("Listado de pacientes: ", pacientes);
//
//        //mapeo entidad a dto
//        return pacientes;
//    }
//    @Override
//    public PacienteSalidaDto buscarPacientePorId(Long id) {
//
//        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
//        PacienteSalidaDto pacienteEncontrado = null;
//
//        if (pacienteBuscado != null){
//            pacienteEncontrado = modelMapper.map(pacienteBuscado, PacienteSalidaDto.class);
//            LOGGER.info("Paciente encontrado");
//        } else LOGGER.error("No se ha encontrado el paciente con id {}", id);
//
//        return pacienteEncontrado;
//    }
//    @Override
//    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
//        if(buscarPacientePorId(id) != null){
//            pacienteRepository.deleteById(id);
//            LOGGER.warn("Se ha eliminado el paciente con id {}", id);
//        }  else {
//            throw new ResourceNotFoundException("No existe registro de paciente con id " + id);
//        }
//
//    }
//    @Override
//    public PacienteSalidaDto actualizarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id) {
//
//        Paciente pacienteRecibido = modelMapper.map(pacienteEntradaDto, Paciente.class);
//        Paciente pacienteAActualizar = pacienteRepository.findById(id).orElse(null);
//        PacienteSalidaDto pacienteSalidaDto = null;
//
//        if(pacienteAActualizar != null){
//
//            pacienteRecibido.setId(pacienteAActualizar.getId());
//            pacienteRecibido.getDomicilio().setId(pacienteAActualizar.getDomicilio().getId());
//            pacienteAActualizar = pacienteRecibido;
//
//            //pacienteAActualizar.setNombre(pacienteRecibido.getNombre());
//            //pacienteAActualizar.setApellido(pacienteRecibido.getApellido());
//            //pacienteAActualizar.setDni(pacienteRecibido.getDni());
//            //pacienteAActualizar.setFechaIngreso(pacienteRecibido.getFechaIngreso());
//            //pacienteAActualizar.getDomicilio().setNumero(pacienteRecibido.getDomicilio().getNumero());
//            //pacienteAActualizar.getDomicilio().setLocalidad(pacienteRecibido.getDomicilio().getLocalidad());
//            //pacienteAActualizar.getDomicilio().setProvincia(pacienteRecibido.getDomicilio().getProvincia());
//
//            pacienteRepository.save(pacienteAActualizar);
//            pacienteSalidaDto = modelMapper.map(pacienteAActualizar, PacienteSalidaDto.class);
//            LOGGER.warn("Paciente actualizado");
//
//        } else {
//            LOGGER.error("No fue posible actualizar el paciente porque no se encuentra en nuestra base de datos");
//            //lanzar excepcion
//        }
//
//        return pacienteSalidaDto;
//    }
//
//
//    private void configureMapping(){
//        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
//                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
//    }
//}

import com.backend.clinica_odontologica.utils.JsonPrinter;

@Service
public class PacienteService implements IPacienteService {
    //se mapea de DTO a entidad y viceversa
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public PacienteSalidaDTO registrarPaciente(PacienteEntradaDto pacienteEntradaDto) {
        //logica de negocio
        //mapeo de dto a entidad
        LOGGER.info("PacienteEntradaDto: " + JsonPrinter.toString(pacienteEntradaDto));
        Paciente paciente = modelMapper.map(pacienteEntradaDto, Paciente.class);
        LOGGER.info("PacienteEntidad: " + JsonPrinter.toString(paciente));
        //Paciente pacienteRegistrado = pacienteIDao.registrar(paciente);
        //mapeo de entidad a dto
        PacienteSalidaDTO pacienteSalidaDTO = modelMapper.map(pacienteRepository.save(paciente), PacienteSalidaDTO.class);
        LOGGER.info("PacienteSalidaDto: " + JsonPrinter.toString(pacienteSalidaDTO));
        return pacienteSalidaDTO;
    }

    @Override
    public List<PacienteSalidaDTO> listarPacientes() {
        //mapeo de lista de entidades a lista de dtos
        List<PacienteSalidaDTO> pacientes = pacienteRepository.findAll()
                .stream()
                .map(paciente -> modelMapper.map(paciente, PacienteSalidaDTO.class))
                .toList();
        LOGGER.info("Listado de todos los pacientes: {}", JsonPrinter.toString(pacientes));

        return pacientes;
    }

    @Override
    public PacienteSalidaDTO buscarPacientePorId(Long id) {

        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteSalidaDTO pacienteEncontrado = null;

        if (pacienteBuscado != null){
            pacienteEncontrado = modelMapper.map(pacienteBuscado, PacienteSalidaDTO.class);
            LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(pacienteEncontrado));
        } else LOGGER.error("No se ha encontrado el paciente con id {}", id);

        return pacienteEncontrado;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if(buscarPacientePorId(id) != null){
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con id {}", id);
        }  else {
            throw new ResourceNotFoundException("No existe registro de paciente con id " + id);
        }

    }

    @Override
    public PacienteSalidaDTO actualizarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id) {

        Paciente pacienteRecibido = modelMapper.map(pacienteEntradaDto, Paciente.class);
        Paciente pacienteAActualizar = pacienteRepository.findById(id).orElse(null);
        PacienteSalidaDTO pacienteSalidaDTO = null;

        if(pacienteAActualizar != null){

            pacienteRecibido.setId(pacienteAActualizar.getId());
            pacienteRecibido.getDomicilio().setId(pacienteAActualizar.getDomicilio().getId());
            pacienteAActualizar = pacienteRecibido;

            //pacienteAActualizar.setNombre(pacienteRecibido.getNombre());
            //pacienteAActualizar.setApellido(pacienteRecibido.getApellido());
            //pacienteAActualizar.setDni(pacienteRecibido.getDni());
            //pacienteAActualizar.setFechaIngreso(pacienteRecibido.getFechaIngreso());
            //pacienteAActualizar.getDomicilio().setNumero(pacienteRecibido.getDomicilio().getNumero());
            //pacienteAActualizar.getDomicilio().setLocalidad(pacienteRecibido.getDomicilio().getLocalidad());
            //pacienteAActualizar.getDomicilio().setProvincia(pacienteRecibido.getDomicilio().getProvincia());

            pacienteRepository.save(pacienteAActualizar);
            pacienteSalidaDTO = modelMapper.map(pacienteAActualizar, PacienteSalidaDTO.class);
            LOGGER.warn("Paciente actualizado: {}", JsonPrinter.toString(pacienteSalidaDTO));

        } else {
            LOGGER.error("No fue posible actualizar el paciente porque no se encuentra en nuestra base de datos");
            //lanzar excepcion
        }

        return pacienteSalidaDTO;
    }


    private void configureMapping(){
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteSalidaDTO.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDTO::setDomicilioSalidaDto));
    }
}

