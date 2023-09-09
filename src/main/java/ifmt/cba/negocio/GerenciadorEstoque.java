package ifmt.cba.negocio;

import java.util.ArrayList;
import ifmt.cba.vo.ProdutoVO;

public class GerenciadorEstoque {

    private ArrayList<ProdutoVO> listaProduto;

    public GerenciadorEstoque() {
        this.listaProduto = new ArrayList<ProdutoVO>();
    }

    public void adicionarProduto(ProdutoVO produtoVO) throws Exception {
        if (produtoVO != null) {
            if (this.buscarProdutoPorCodigo(produtoVO.getCodigo()) == null) {
                this.listaProduto.add(produtoVO);
            } else {
                throw new Exception("Produto ja existe");
            }

        } else {
            throw new Exception("produto nao pode ser nulo");
        }
    }
    public float totalizarValorEstoqueProduto(ProdutoVO produtoVO, int quantidade, float valorUnitario) throws Exception {
        if (produtoVO != null &&  quantidade> 0 && valorUnitario > 0) {
            return quantidade * valorUnitario;
        } else {
            throw new Exception("Produto inválido para o cálculo do valor total do estoque");
        }
    }

    public float totalizarValorEstoqueGeral() throws Exception{
        float total = 0;
      for(ProdutoVO produto : this.listaProduto){
        total += produto.getEstoque() * produto.getValorUnitario();
      }
      return total;
    }
    

    public void removerProduto(ProdutoVO produtoVO) throws Exception {
        if (produtoVO != null) {
            if (this.listaProduto.indexOf(produtoVO) >= 0) {
                this.listaProduto.remove(produtoVO);
            } else {
                throw new Exception("produto nao localizado");
            }
        } else {
            throw new Exception("produto nao pode ser nulo");
        }
    }

    public void adicionarEstoqueProduto(ProdutoVO produtoVO, int quantidade) throws Exception {
        if (produtoVO != null || quantidade > 0) {
            if (this.listaProduto.indexOf(produtoVO) >= 0) {
                ProdutoVO produtoVoTemp = this.listaProduto.get(this.listaProduto
                        .indexOf(produtoVO));
                produtoVoTemp.adicionarEstoque(quantidade);
            } else {
                throw new Exception("produto nao localizado");
            }
        } else {
            throw new Exception("produto ou quantidade inconsistente");
        }
    }

    public void baixarEstoqueProduto(ProdutoVO produtoVO, int quantidade) throws Exception {
        if (produtoVO != null || quantidade > 0) {
            if (this.listaProduto.indexOf(produtoVO) >= 0) {
                ProdutoVO produtoVoTemp = this.listaProduto.get(this.listaProduto.indexOf(produtoVO));
                produtoVoTemp.baixarEstoque(quantidade);
            } else {
                throw new Exception("produto nao localizado");
            }
        } else {
            throw new Exception("produto ou quantidade inconsistente");
        }

    }

    public ProdutoVO buscarProdutoPorCodigo(int codigo) {

        ProdutoVO produtoVoTemp = null;

        for (ProdutoVO produtoVO : this.listaProduto) {
            if (produtoVO.getCodigo() == codigo) {
                produtoVoTemp = produtoVO;
                break;
            }
        }
        return produtoVoTemp;
    }

    public int contadorProduto() {
        return this.listaProduto.size();
    }

    public ArrayList<ProdutoVO> listaProduto() {
        return this.listaProduto;
    }

    public int totalEstoqueFisico() {
        int total = 0;
        for (ProdutoVO produtoVO : this.listaProduto) {
            total += produtoVO.getEstoque();
        }
        return total;
    }
}
