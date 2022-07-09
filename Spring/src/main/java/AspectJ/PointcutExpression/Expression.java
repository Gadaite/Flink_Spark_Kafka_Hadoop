package AspectJ.PointcutExpression;

public class Expression {
    /**
     * execution([权限修饰符][返回类型][方法名称])([参数列表])
     * eg:
     * 对包中AspectJ.PointcutExpression.PackageOne.Dao.BookDao类的add方法增强
     * execution(public/private/*.AspectJ.PointcutExpression.PackageOne.Dao.BookDao.add(..))
     *
     * 对包中AspectJ.PointcutExpression.PackageOne.Dao.BookDao类的所有方法增强
     * execution(public/private/*.AspectJ.PointcutExpression.PackageOne.Dao.BookDao.*(..))
     *
     * 对包AspectJ.PointcutExpression.Dao中所有类的所有方法增强
     * execution(public/private/*.AspectJ.PointcutExpression.PackageOne.Dao.*.*(..))
     */
}
