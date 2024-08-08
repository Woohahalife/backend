package com.core.backend.group;

import org.springframework.beans.factory.annotation.Autowired;

import com.core.backend.ServiceTestEnvSupport;
import com.core.backend.group.application.GroupCommandService;
import com.core.backend.group.application.GroupQueryService;
import com.core.backend.group.infra.JpaGroupRepository;
import com.core.backend.user.infra.database.JpaUserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class GroupServiceTestFixture extends ServiceTestEnvSupport {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	protected GroupCommandService groupCommandService;

	@Autowired
	protected GroupQueryService groupQueryService;

	@Autowired
	protected JpaGroupRepository groupRepository;

	@Autowired
	protected JpaUserRepository userRepository;
}
