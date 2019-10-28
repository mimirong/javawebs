ALTER TABLE pm_project ADD COLUMN begin_year int(4);
ALTER TABLE pm_project ADD COLUMN end_year int(4);
UPDATE pm_project p SET begin_year = LEFT(begin_end,4);
UPDATE pm_project p SET end_year = RIGHT(begin_end,4);