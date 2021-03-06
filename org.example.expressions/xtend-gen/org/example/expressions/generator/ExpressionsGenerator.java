/**
 * generated by Xtext
 */
package org.example.expressions.generator;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.example.expressions.expressions.AbstractElement;
import org.example.expressions.expressions.ExpressionsModel;
import org.example.expressions.interpreter.ExpressionsInterpreter;

@SuppressWarnings("all")
public class ExpressionsGenerator implements IGenerator {
  @Inject
  @Extension
  private ExpressionsInterpreter _expressionsInterpreter;
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<ExpressionsModel> _filter = Iterables.<ExpressionsModel>filter(_iterable, ExpressionsModel.class);
    final Procedure1<ExpressionsModel> _function = new Procedure1<ExpressionsModel>() {
      @Override
      public void apply(final ExpressionsModel it) {
        StringConcatenation _builder = new StringConcatenation();
        URI _uRI = resource.getURI();
        String _lastSegment = _uRI.lastSegment();
        _builder.append(_lastSegment, "");
        _builder.append(".evaluated");
        String _interpretExpressions = ExpressionsGenerator.this.interpretExpressions(it);
        fsa.generateFile(_builder.toString(), _interpretExpressions);
      }
    };
    IterableExtensions.<ExpressionsModel>forEach(_filter, _function);
  }
  
  public String interpretExpressions(final ExpressionsModel model) {
    EList<AbstractElement> _elements = model.getElements();
    final Function1<AbstractElement, String> _function = new Function1<AbstractElement, String>() {
      @Override
      public String apply(final AbstractElement it) {
        StringConcatenation _builder = new StringConcatenation();
        ICompositeNode _node = NodeModelUtils.getNode(it);
        String _tokenText = NodeModelUtils.getTokenText(_node);
        _builder.append(_tokenText, "");
        _builder.append(" ~> ");
        Object _interpret = ExpressionsGenerator.this._expressionsInterpreter.interpret(it);
        _builder.append(_interpret, "");
        return _builder.toString();
      }
    };
    List<String> _map = ListExtensions.<AbstractElement, String>map(_elements, _function);
    return IterableExtensions.join(_map, "\n");
  }
}
