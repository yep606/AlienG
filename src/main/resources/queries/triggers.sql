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

CREATE TRIGGER task_AFTER_DELETE AFTER DELETE ON task FOR EACH ROW EXECUTE PROCEDURE after_func_delete();

--AFTER UPDATE

CREATE OR REPLACE FUNCTION after_func_update() RETURNS TRIGGER AS $$
    BEGIN
        if(coalesce(OLD.completed,0) <> coalesce(NEW.completed,0) AND NEW.completed=1 AND coalesce(OLD.category_id,0) = coalesce(NEW.category_id,0) )
        then
            UPDATE category SET uncompleted_count = (coalesce(uncompleted_count,0)-1), completed_count = (coalesce(completed_count,0)+1) WHERE id = OLD.category_id;
            UPDATE stat SET uncompleted_total = (coalesce(uncompleted_total,0)-1), completed_total = (coalesce(completed_total,0)+1) WHERE id=1;
        end if;

        if(coalesce(OLD.completed,0) <> coalesce(NEW.completed,0) AND NEW.completed=0 AND coalesce(OLD.category_id,0) = coalesce(NEW.category_id,0) )
        then
            UPDATE category SET uncompleted_count = (coalesce(uncompleted_count,0)+1), completed_count = (coalesce(completed_count,0)-1) WHERE id = OLD.category_id;
            UPDATE stat SET uncompleted_total = (coalesce(uncompleted_total,0)+1), completed_total = (coalesce(completed_total,0)-1) WHERE id=1;
        end if;




        /*Changed category for completed=1 */
        if(coalesce(OLD.completed,0) = coalesce(NEW.completed,0) AND NEW.completed=1 AND coalesce(OLD.category_id,0) <> coalesce(NEW.category_id,0) )
        then
            UPDATE category SET completed_count = (coalesce(completed_count,0)-1) WHERE id = OLD.category_id;
            UPDATE category SET completed_count = (coalesce(completed_count,0)+1) WHERE id = NEW.category_id;
        end if;




        /*Changed category for completed=0 */
         if(coalesce(OLD.completed,0) = coalesce(NEW.completed,0) AND NEW.completed=0 AND coalesce(OLD.category_id,0) <> coalesce(NEW.category_id,0) )
        then
            UPDATE category SET uncompleted_count = (coalesce(uncompleted_count,0)-1) WHERE id = OLD.category_id;
            UPDATE category SET uncompleted_count = (coalesce(uncompleted_count,0)+1) WHERE id = NEW.category_id;
        end if;

        /*Changed category, changed completed from 1 to 0 */

        /*Changed category, changed completed from 0 to 1 */





