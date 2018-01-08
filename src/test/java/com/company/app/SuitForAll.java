package com.company.app;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({
        CreateFileTestFunction.class
})
@RunWith(Categories.class)
//@Categories.IncludeCategory(MyCategories.Negative.class)
//@Categories.ExcludeCategory(MyCategories.Positive.class)
public class SuitForAll {
}
