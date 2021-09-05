package com.frank.sfwebpage.services.map;

import com.frank.sfwebpage.model.Visit;
import com.frank.sfwebpage.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {
}