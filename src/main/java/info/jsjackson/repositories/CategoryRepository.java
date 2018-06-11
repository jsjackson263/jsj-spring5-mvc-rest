package info.jsjackson.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import info.jsjackson.domain.Category;

/**
 * @author jsjackson
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
