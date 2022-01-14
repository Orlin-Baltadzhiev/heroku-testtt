package bg.DeveloperGroup.musicdb.Service;

import bg.DeveloperGroup.musicdb.models.Service.LogServiceModel;

import java.util.*;

public interface LogService {
    void createLog(String action, Long albumId);

    List<LogServiceModel> findAllLogs();
}
