package com.example.shift_management;

import org.springframework.data.jpa.repository.JpaRepository;
/*/
データベースとのやり取りをインターフェース。saveやfindAllなどの
機能はSpringBootが自動的に用意してくれる。
*/
public interface DbRepository extends JpaRepository<DbEntity, Long> {
}
