package com.example.demo.service;

import com.example.demo.entity.UniversityEntity;
import com.example.demo.model.receive.UniversityReceiveDTO;
import com.example.demo.model.response.ApiResponse;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniversityService implements BaseService<UniversityReceiveDTO> {
    private final UniversityRepository repository;
    private final ModelMapper modelMapper;
    private final ResponseUtils responseUtils;

   public boolean check(String name){
       return repository.findByName(name).isPresent();
   }
   public boolean check(long id){
       return repository.findById(id).isPresent();
   }

    @Override
    public ApiResponse add(UniversityReceiveDTO universityReceiveDTO) {
        if (check(universityReceiveDTO.getName()))
            return responseUtils.UNIVERSITY_EXISTS;

        UniversityEntity university=modelMapper.map(universityReceiveDTO,UniversityEntity.class);
        ApiResponse success=responseUtils.SUCCESS;
        success.setData(repository.save(university));
        return success;
    }

    @Override
    public ApiResponse get(long id) {
       if (!check(id))
        return responseUtils.UNIVERSITY_NOT_FOUND;
       ApiResponse success=responseUtils.SUCCESS;
       success.setData(repository.findById(id).get());
       return success;
    }

    @Override
    public ApiResponse list() {
        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.findAll());
        return success ;
    }

    @Override
    public ApiResponse update(long id, UniversityReceiveDTO universityReceiveDTO) {
       if (!check(id))
        return responseUtils.UNIVERSITY_NOT_FOUND;
       UniversityEntity university=repository.findById(id).get();
       if (universityReceiveDTO.getName()!=null){
           if (check(universityReceiveDTO.getName()))
               return responseUtils.UNIVERSITY_EXISTS;
           university.setName(universityReceiveDTO.getName());}
       if (universityReceiveDTO.getAddress()!=null)
           university.setAddress(universityReceiveDTO.getAddress());
       if (universityReceiveDTO.getOpenYear() !=0L)
           university.setOpenYear(universityReceiveDTO.getOpenYear());

        ApiResponse success = responseUtils.SUCCESS;
        success.setData(repository.save(university));
        return success;
    }

    @Override
    public ApiResponse delete(long id) {
       if (!check(id))
           return responseUtils.UNIVERSITY_NOT_FOUND;
       repository.deleteById(id);
        return responseUtils.SUCCESS;
    }
}
