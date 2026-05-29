package com.jhona.appsubirarchivos.repo;

import com.jhona.appsubirarchivos.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface
FileRepository extends JpaRepository<FileEntity, UUID> {

}
