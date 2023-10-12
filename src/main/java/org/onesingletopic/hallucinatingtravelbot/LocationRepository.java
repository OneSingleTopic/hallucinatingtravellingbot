package org.onesingletopic.hallucinatingtravelbot;

import org.onesingletopic.hallucinatingtravelbot.repository.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {}
