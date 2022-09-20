package com.example.washer.repository;


import com.example.washer.model.Journal;
import com.example.washer.projection.JournalProjection;
import com.example.washer.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JournalRepository extends BaseRepository<Journal> {

    @Query(nativeQuery = true,
            value = "select *\n" +
                    "from saveJournalJsonData(:change)")
    Boolean saveObjectJson(String change);
    /*
    create or replace function saveJournalJsonData(change_text text) returns boolean
    language plpgsql
        as
        $$
        declare
            json_text json;
        begin
            begin
                json_text := change_text;
            EXCEPTION
                WHEN others THEN
                    RETURN FALSE;
            END;
            insert into journals(change) values (json_text);
            return true;
     end
     $$;
     */


    @Query(nativeQuery = true,
            value = "select cast(j.id as varchar),\n" +
                    "       cast(j.change as text) as changes\n" +
                    "from journals j, orders o\n" +
                    "join wash_company wc on o.wash_company_id = wc.id\n" +
                    "where wc.id = :washCompanyId and\n" +
                    "    cast(cast(change as json)->'orderId' as text) = concat('\"',o.id, '\"')\n" +
                    "LIMIT :pageSize\n" +
                    "    OFFSET ((:pageNumber-1) * :pageSize)")
    List<JournalProjection> getAllJournalByCompanyId(UUID washCompanyId, int pageNumber, int pageSize);
}
