package org.gfg.musicapplication.repositories;

import org.gfg.musicapplication.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository <Feedback, Integer> {
}
