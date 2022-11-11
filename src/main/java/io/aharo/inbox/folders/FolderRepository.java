package io.aharo.inbox.folders;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends CassandraRepository<Folder, String>           // interface is what mapps the code to the cloud DB 
{
    List<Folder> findAllById(String id);
} 