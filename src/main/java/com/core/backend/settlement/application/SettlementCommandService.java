package com.core.backend.settlement.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.settlement.domain.SettlementRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SettlementCommandService {

	private final SettlementRepository settlementRepository;


}
