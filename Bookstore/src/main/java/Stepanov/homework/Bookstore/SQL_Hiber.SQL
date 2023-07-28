select
ordering0_.id as id1_4_0_,
ordering0_.buyer_id as buyer_id2_4_0_,

 ordering0_."id" + 1 as formula1_0_,

 buyer1_.id as id1_3_1_,
  buyer1_.birth_date as birth_da2_3_1_,
   buyer1_.middle_name as middle_n3_3_1_,
   buyer1_.name as name4_3_1_,
   buyer1_.surname as surname5_3_1_,
   orderingde2_.ordering_id as ordering5_5_2_,
   orderingde2_.id as id1_5_2_,
   orderingde2_.id as id1_5_3_,
   orderingde2_.book_id as book_id3_5_3_,
    orderingde2_.price as price4_5_3_,
    orderingde2_.ordering_id as ordering5_5_3_,
     orderingde2_.quantity as quantity2_5_3_,
     book3_.id as id1_1_4_,
     book3_.author_id as author_i6_1_4_,
      book3_.pages as pages2_1_4_,
      book3_.price as price3_1_4_,
      book3_.title as title4_1_4_,
       book3_.year_of_publication as year_of_5_1_4_,
       author4_.id as id1_0_5_,
       author4_.middle_name as middle_n2_0_5_,
       author4_.name as name3_0_5_,
       author4_.surname as surname4_0_5_
        from ordering ordering0_

        inner join buyer buyer1_ on ordering0_.buyer_id=buyer1_.id
        left outer join ordering_details orderingde2_ on ordering0_.id=orderingde2_.ordering_id
        left outer join book book3_ on orderingde2_.book_id=book3_.id and orderingde2_.price=book3_.price
        left outer join author author4_ on book3_.author_id=author4_.id where ordering0_.id=?