package persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {
		public TDSFactoriaDAO() {
		}
			
			@Override
			public IAdaptadorUsuario getUsuarioDAO() {
				return AdaptadorUsuario.getUnicaInstancia();
			}
			
			@Override
			public IAdaptadorVideo getVideoDAO() {
				return AdaptadorVideo.getUnicaInstancia();
			}
			
			@Override
			public IAdaptadorListaVideos getListaVideoDAO() {
				return AdaptadorListaVideos.getUnicaInstancia();
			}

			@Override
			public IAdaptadorEtiqueta getEtiquetaDAO() {
				return AdaptadorEtiqueta.getUnicaInstancia();
			}
		
		

	}
