CREATE OR REPLACE FUNCTION after_func() RETURNS TRIGGER AS $$
    BEGIN
       if((coalesce(NEW.category_id,0) > 0) AND (coalesce(NEW.completed,0)=1))
         then
         UPDATE category SET completed_count =(coalesce(completed_count,0)+1)
         where id = NEW.category_id;
         end if;

         if((coalesce(NEW.category_id,0) > 0) AND (coalesce(NEW.completed,0)=0))
         then
         UPDATE category SET uncompleted_count =(coalesce(uncompleted_count,0)+1)
         where id = NEW.category_id;
         end if;

         if(coalesce(NEW.completed,0) = 1)
         then
           UPDATE stat SET completed_total = (coalesce(completed_total,0)+1) where id=1;
         else
           UPDATE stat SET uncompleted_total = (coalesce(uncompleted_total,0)+1) where id=1;
         end if;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER task_AFTER_INSERT AFTER INSERT ON task FOR EACH ROW EXECUTE PROCEDURE after_func();

-- AFTER DELETE

CREATE OR REPLACE FUNCTION after_func_delete() RETURNS TRIGGER AS $$
    BEGIN
       if((coalesce(OLD.category_id,0) > 0) AND (coalesce(OLD.completed,0)=1))
         then
         UPDATE category SET completed_count =(coalesce(completed_count,0)-1)
         where id = OLD.category_id;
         end if;

         if((coalesce(OLD.category_id,0) > 0) AND (coalesce(OLD.completed,0)=0))
         then
         UPDATE category SET uncompleted_count =(coalesce(uncompleted_count,0)-1)
         where id = OLD.category_id;
         end if;

         if(coalesce(OLD.completed,0) = 1)
         then
           UPDATE stat SET completed_total = (coalesce(completed_total,0)-1) where id=1;
         else
           UPDATE stat SET uncompleted_total = (coalesce(uncompleted_total,0)-1) where id=1;
         end if;
    END;
$$ LANGUAGE plpgsql;



